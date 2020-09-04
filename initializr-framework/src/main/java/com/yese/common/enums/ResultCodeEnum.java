package com.yese.common.enums;


import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 自定义响应码枚举
 * 默认响应参考{@link ApiErrorCode}
 *
 * @author zhangqingfu
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnum implements IErrorCode {

    /**
     * 通用状态码可以参考{@link HttpStatus}
     */
    // 1xx：相关信息
    // 2xx：操作成功
    // 3xx：重定向
    // 4xx：客户端错误
    BAD_REQUEST(400, "错误请求"),
    NOT_FOUND(404, "资源不存在"),
    METHOD_NOT_ALLOWED(405, "不支持的请求类型"),
    // 5xx：服务器错误
    INTERNAL_SERVER_ERROR(500, "内部服务器错误"),


    // 1000～1999区间表示参数错误
    PARAMETER_ERROR(1000, "参数错误"),
    PARAMETER_VALIDATE_FAIL(1001, "参数校验失败"),
    PARAMETER_BLANK(1002, "参数为空"),

    // 2000～2999区间表示用户错误
    USER_ERROR(2000, "用户错误"),
    USER_NOT_EXIST(2001, "用户不存在"),
    USER_LOCKED(2002, "账户被锁定，请联系管理员!"),
    USER_CREDENTIALS_EXPIRED(2003, "密码过期，请联系管理员!"),
    USER_ACCOUNT_EXPIRED(2004, "账户过期，请联系管理员!"),
    USER_DISABLED(2005, "账户被禁用，请联系管理员!"),
    USER_BAD_CREDENTIALS(2006, "用户名或者密码输入错误，请重新输入!"),
    USER_NOT_LOGIN(2007, "尚未登录，请先登录!"),
    VERIFY_CODE_ERROR(2008, "验证码不正确"),

    // 3000～3999区间表示接口错误
    API_ERROR(3000, "接口错误"),
    BUSINESS_ERROR(3001, "自定义业务异常"),


    ;

    /**
     * 响应码
     */
    private final long code;

    /**
     * 提示消息
     */
    private final String msg;

}