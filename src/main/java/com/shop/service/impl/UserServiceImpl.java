package com.shop.service.impl;

import com.shop.common.Const;
import com.shop.common.ResponseCode;
import com.shop.common.ServerResponse;
import com.shop.common.TokenCache;
import com.shop.dao.UserMapper;
import com.shop.pojo.User;
import com.shop.service.UserService;
import com.shop.utils.Check;
import com.shop.utils.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
        if (userMapper.checkUsername(username) == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        //对密码进行加密 与数据库中已加密的密码  进行比对
        String md5PassWord = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username,md5PassWord);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),"密码错误");
        }
        //使得前台json数据中无法看到密码
        user.setPassword(null);
        return ServerResponse.createBySuccess(user);
    }

    @Override
    public ServerResponse<String> register(User user) {
        // 检查用户名是否存在
        if (userMapper.checkUsername(user.getUsername()) > 0){
            return ServerResponse.createByErrorMessage("用户名已存在");
        }
        // 检查邮箱是否存在
        if (userMapper.checkEmail(user.getEmail()) > 0) {
            return ServerResponse.createByErrorMessage("邮箱已存在");
        }
        //注册的用户默认为普通角色
        user.setRole(Const.Role.ROLE_CUSTOMER);
        //对密码进行MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        if (userMapper.insert(user) == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        //校检参数类型字符串type不等于null
        if (StringUtils.isNotBlank(type)) {
            //校检用户名
            if (Const.USERNAME.equals(type)) {
               int resultsCount = userMapper.checkUsername(str);
               if (resultsCount > 0) {
                   return ServerResponse.createByErrorMessage("用户名已存在");
               }
            }
            //校检邮箱
            if (Const.EMAIL.equals(type)) {
                //邮箱匹配正则表达式
                String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
                //对邮箱进行正则表达式的判断
                boolean result = Check.checkString(str, RULE_EMAIL);
                if (!result) {
                    return ServerResponse.createByErrorMessage("邮箱格式不正确");
                }
                int resultsCount = userMapper.checkEmail(str);
                if(resultsCount > 0) {
                    return ServerResponse.createByErrorMessage("邮箱已存在");
                }
            }
        //参数类型字符串type等于null
        } else {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccess("校检成功");
    }

    @Override
    public ServerResponse<String> selectQuestion(String username) {
        //校检用户名是否存在
        ServerResponse<String> valid = this.checkValid(username, Const.USERNAME);
        if (valid.isSuccess()) {
            //用户名不存在
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        String question = userMapper.selectQuestionByUsername(username);
        //判断question是否等于null
        if (StringUtils.isNotBlank(question)) {
            return ServerResponse.createBySuccess(question);
        }
        return ServerResponse.createByErrorMessage("找回密码的问题是空的");
    }

    @Override
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        int resultsCount = userMapper.checkAnswer(username, question, answer);
        if (resultsCount > 0) {
            //用户名和问题及答案都是正确的
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(username, forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("问题答案错误");
    }

    public static void main(String[] args) {
        String forgetToken = UUID.randomUUID().toString();
        System.out.println(forgetToken);
    }
}
