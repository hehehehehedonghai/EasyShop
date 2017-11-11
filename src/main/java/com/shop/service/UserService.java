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

    /**
     * register
     * 用户注册
     * @param user
     * @return
     */
    ServerResponse<String> register(User user);

    /**
     * 校检用户名或者邮箱 是否存在
     * @param str 要校检的字符串
     * @param type 字符串的类型
     * @return
     */
    ServerResponse<String> checkValid(String str, String type);

    ServerResponse<String> selectQuestion(String username);

    ServerResponse<String> checkAnswer(String username, String question, String answer);
}
