package com.shop.service;

import com.shop.common.ServerResponse;
import com.shop.pojo.User;
/**
 * UserService
 * 用户模块接口
 * @author Yarn
 * @create 2017/11/10/11:12
 */
public interface UserService {
    /**
     * login
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    ServerResponse<User> login(String username, String password);
}
