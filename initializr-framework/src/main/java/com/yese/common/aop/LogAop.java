package com.yese.common.aop;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import com.yese.common.annotation.Log;
import com.yese.common.entity.SysLog;
import com.yese.common.utils.ServletUtils;
import com.yese.common.utils.ip.AddressUtils;
import com.yese.common.utils.ip.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.StringJoiner;

/**
 * 操作日志记录处理
 *
 * @author zhangqingfu
 */
@Aspect
@Component
@Slf4j
public class LogAop {

    /**
     * 线程本地变量,存储方法开始执行时间
     */
    private static final ThreadLocal<Date> LOG_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 配置织入点
     */
    @Pointcut("@annotation(com.yese.common.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * 处理请求前执行
     *
     * @param joinPoint 切点
     */
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        Date beginTime = new Date();
        LOG_THREAD_LOCAL.set(beginTime);
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     * @param responseJson controller方法返回值
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "responseJson")
    public void doAfterReturning(JoinPoint joinPoint, Object responseJson) {
        Date endTime = new Date();
        handleLog(joinPoint, null, responseJson, endTime);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null, null);
    }

    /**
     *
     * @param joinPoint 切点
     * @param e 异常
     * @param responseJson 方法返回值
     * @param endTime 结束时间
     */
    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object responseJson, Date endTime) {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            // 获得注解
            Log controllerLog = method.getAnnotation(Log.class);
            if (controllerLog == null) {
                return;
            }
            HttpServletRequest request = ServletUtils.getRequest();

            // 获取当前的用户
            //LoginUser loginUser = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());

            // *========数据库日志=========*//
            SysLog sysLog = new SysLog();
            sysLog.setStatus(ApiErrorCode.SUCCESS.getCode());
            String ip = IpUtils.getIpAddr(request);
            sysLog.setIp(ip);
            sysLog.setLocation(AddressUtils.getRealAddressByIP(ip));
            sysLog.setRequestUrl(request.getRequestURI());
            // 请求的方法名
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = signature.getName();
            sysLog.setMethod(className + "." + methodName + "()");
            sysLog.setRequestType(request.getMethod());
            sysLog.setResponseJson(JSON.toJSONString(responseJson));
            // 有异常说明请求失败了
            if (e != null) {
                sysLog.setStatus(ApiErrorCode.FAILED.getCode());
                sysLog.setErrorMsg(e.getMessage());
            }
            Date beginTime = LOG_THREAD_LOCAL.get();
            LOG_THREAD_LOCAL.remove();

            sysLog.setBeginTime(beginTime);
            if (!ObjectUtils.isEmpty(endTime)) {
                sysLog.setEndTime(endTime);
                sysLog.setCostTime(endTime.getTime() - beginTime.getTime());
            }


            // if (loginUser != null) {
            //     sysLog.setOperName(loginUser.getUsername());
            // }

            // 处理@Log注解上的参数
            getControllerMethodDescription(joinPoint, controllerLog, sysLog);
            log.info("sysLog:[{}]", sysLog);

            // 保存数据库
            // AsyncManager.me().execute(AsyncFactory.recordOper(sysLog));
        } catch (Exception exception) {
            log.error("controller层日志记录异常", exception);
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log 日志
     * @param sysLog 操作日志
     * @throws Exception 异常
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, Log log, SysLog sysLog) throws Exception {
        sysLog.setModule(log.module());
        sysLog.setContent(log.content());
        sysLog.setBusinessType(log.businessType().getCode());
        // 是否需要保存请求的参数
        if (log.isSaveRequestData()) {
            setRequestValue(joinPoint, sysLog);
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param sysLog 操作日志
     */
    private void setRequestValue(JoinPoint joinPoint, SysLog sysLog) {
        String requestMethod = sysLog.getRequestType();

        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            // 获取post的json参数
            String params = argsArrayToString(joinPoint.getArgs());
            sysLog.setRequestParam(params);
        } else {
            StringJoiner params = new StringJoiner(",");
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            // 请求的方法参数值
            Object[] args = joinPoint.getArgs();
            // 请求的方法参数名称
            LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
            String[] paramNames = u.getParameterNames(method);
            if (args != null && paramNames != null) {
                for (int i = 0; i < args.length; i++) {
                    params.add(paramNames[i] + "=" + args[i]);
                }
            }
            if (StrUtil.isNotBlank(params.toString())) {
                sysLog.setRequestParam(params.toString());
            }
        }
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null && paramsArray.length > 0) {
            for (Object o : paramsArray) {
                if (!isFilterObject(o)) {
                    Object jsonObj = JSON.toJSON(o);
                    params.append(jsonObj.toString()).append(" ");
                }
            }
        }
        return params.toString().trim();
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    public boolean isFilterObject(final Object o) {
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse;
    }


}
