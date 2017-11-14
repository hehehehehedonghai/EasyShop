package com.shop.dao;

import com.shop.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * UserMapper
 * 用户模块 持久层接口
 * @author Yarn
 * @create 2017/11/10/11:15
 */
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * checkUsername
     * 检查用户名是否存在
     * @param username 用户名
     * @return
     */
    int checkUsername(String username);

    int checkEmail(String email);

    /**
     * selectLogin
     * 查询尝试登录的用户信息
     * 没有查询用户则返回null
     * @param username 用户名
     * @param password 密码
     * @return
     */
    User selectLogin(@Param("username") String username, @Param("password")String password);

    String selectQuestionByUsername(String username);

    int checkAnswer(@Param("username")String username,@Param("question")String question,@Param("answer")String answer);

    int updatePasswordByUsername(@Param("username")String username,@Param("passwordNew")String passwordNew);

    int checkPassword(@Param(value="password")String password,@Param("userId")Integer userId);

    int checkEmailByUserId(@Param(value="email")String email,@Param(value="userId")Integer userId);


    User selectadminRoleLogin(String username, String md5PassWord);
}
