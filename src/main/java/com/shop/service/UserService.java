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
     * 管理员角色登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    ServerResponse<User> adminRoleLogin(String username, String password);


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

    /**
     * 获取用户重置密码问题
     * @param username
     * @return
     */
    ServerResponse<String> selectQuestion(String username);

    /**
     * checkAnswer
     * 用户重置密码  参数校检
     * @param username 用户名
     * @param question 用户问题
     * @param answer  用户答案
     * @return
     */
    ServerResponse<String> checkAnswer(String username, String question, String answer);

    /**
     * 未登录状态下的 重置密码
     * @param username 用户名
     * @param passwordNew 密码
     * @param forgetToken 唯一凭证
     * @return
     */
    ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);

    /**
     * 登录状态下的 重置密码
     * @param passwordOld 旧密码
     * @param passwordNew 新密码
     * @param user 用户对象
     * @return
     */
    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);


}
