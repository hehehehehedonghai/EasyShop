package com.shop.service.impl;

import com.shop.common.ResponseCode;
import com.shop.common.ServerResponse;
import com.shop.dao.UserMapper;
import com.shop.pojo.User;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 * 用户业务层接口实现类
 * @author Yarn
 * @create 2017/11/10/11:15
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {
        // 检查用户名是否存在
        if (userMapper.checkUsername(username) == 0){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        User user = userMapper.selectLogin(username,password);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),"密码错误");
        }
        //使得前台json数据中无法看到密码
        user.setPassword(null);
        return ServerResponse.createBySuccess(user);
    }
}
