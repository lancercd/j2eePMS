package com.j2ee.annotation;

import com.j2ee.dto.LoginType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 验证登录的类型
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {

    /**
     * 要求登录的类型
     *          登录的类型必须符合LoginType[]中的类型
     */
    LoginType[] type();

}
