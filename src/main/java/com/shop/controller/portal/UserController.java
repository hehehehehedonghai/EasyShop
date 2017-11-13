package com.shop.controller.portal;

import com.shop.common.Const;
import com.shop.common.ServerResponse;
import com.shop.pojo.User;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * UserController
 * 用户模块
 * @author Yarn
 * @create 2017/11/10/11:12
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> loginUser = userService.login(username,password);
        if (loginUser.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER,loginUser.getData());
        }
        System.out.println(loginUser);
        return loginUser;
    }

    /**
     * 获取登陆用户信息
     * @param session
     * @return
     */
    @RequestMapping(value = "getUserInfo.do",method = RequestMethod.GET)
    public ServerResponse<User> getUserInfo(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登陆");
    }


    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping(value = "register.do",method = RequestMethod.POST)
    public ServerResponse<String> register(User user){
        return userService.register(user);
    }

    /**
     * 用户退出登录
     * @param session
     * @return
     */
    @RequestMapping(value = "logout.do",method = RequestMethod.GET)
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    /**
     * 校检用户名或者邮箱是否存在
     * @param str 要校检的字符串
     * @param type 字符串的类型 username  or  email
     * @return
     */
    @RequestMapping(value = "checkValid.do",method = RequestMethod.GET)
    public ServerResponse<String> checkUserNameOrEmail(String str, String type){
        return userService.checkValid(str,type);
    }

    /**
     * 获取用户重置密码问题
     * @param username 用户名
     * @return
     */
    @RequestMapping(value = "getQuestion.do",method = RequestMethod.GET)
    public ServerResponse<String> getQuestion(String username){
       return  userService.selectQuestion(username);
    }

    /**
     * @param username 用户名
     * @param question 用户问题
     * @param answer   用户输入的答案
     * @return
     */
    @RequestMapping(value = "getQuestionAnswer.do",method = RequestMethod.GET)
    public ServerResponse<String> getQuestionAnswer(String username, String question, String answer ){
        return userService.checkAnswer(username, question, answer);
    }

    /**
     * 未登录状态下 用户重置密码
     * @param username 用户名
     * @param passwordNew 新密码
     * @param forgetToken 唯一凭证
     * @return
     */
    @RequestMapping(value = "forgetResetPassword.do",method = RequestMethod.GET)
    public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken){
        return userService.forgetResetPassword(username, passwordNew, forgetToken);
    }

    /**
     * 登录状态下的 重置密码
     * @param passwordOld 旧密码
     * @param passwordNew 新密码
     * @return
     */
    @RequestMapping(value = "resetPassword.do",method = RequestMethod.GET)
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return userService.resetPassword(passwordOld, passwordNew, user);
    }




}
