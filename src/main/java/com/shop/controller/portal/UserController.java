package com.shop.controller.portal;

import com.shop.common.Const;
import com.shop.common.ServerResponse;
import com.shop.pojo.User;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
     * 查询到用户则放至session中
     * @param username 用户名
     * @param password 密码
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do",method = RequestMethod.GET)
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> loginUser = userService.login(username,password);
        if(loginUser.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,loginUser.getData());
        }
        return loginUser;
    }
}
