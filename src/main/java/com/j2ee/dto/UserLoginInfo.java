package com.j2ee.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户简单登录信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginInfo {

    /**
     * 用户id
     */
    Integer uid;

    /**
     * 登录用户类型
     *      枚举
     */
    LoginType type;

    /**
     * 用户名
     */
    String username;
}
