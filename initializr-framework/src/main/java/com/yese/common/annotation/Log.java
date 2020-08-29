package com.yese.common.annotation;


import com.yese.common.enums.BusinessType;

import java.lang.annotation.*;

/**
 * controller层自定义日志记录注解
 *
 * @author zhangqingfu
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块名称
     */
    String module() default "";

    /**
     * 日志内容
     */
    String content() default "";

    /**
     * 业务操作类型
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;

}
