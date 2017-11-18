package com.shop.dao;

import com.shop.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * UserMapper
 * 用户模块 持久层接口
 * @author Yarn
 * @create 2017/11/9/11:15
 */
public interface UserMapper {

    /**
     * 根据主键删除用户
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增用户
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 有条件性的新增
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 根据主键查询用户
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 按主键选择性更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 按主键更新全部
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);

    /**
     * checkUsername
     * 检查用户名是否存在
     * @param username 用户名
     * @return
     */
    int checkUsername(String username);

    /**
     * 校检邮箱是否存在
     * @param email
     * @return
     */
    int checkEmail(String email);

    /**
     * 查询尝试登录的用户信息
     * 没有查询用户则返回null
     * @param username 用户名
     * @param password 密码
     * @return
     */
    User selectLogin(@Param("username") String username, @Param("password")String password);

    /**
     * 根据用户名查询用户重置密码的问题
     * @param username
     * @return
     */
    String selectQuestionByUsername(String username);

    /**
     * 检查答案是否正确
     * @param username 用户名
     * @param question 问题
     * @param answer   答案
     * @return
     */
    int checkAnswer(@Param("username")String username,@Param("question")String question,@Param("answer")String answer);

    /**
     * 通过用户名更改密码
     * @param username 用户名
     * @param passwordNew 新密码
     * @return
     */
    int updatePasswordByUsername(@Param("username")String username,@Param("passwordNew")String passwordNew);

    /**
     * 根据用户Id 校检密码是否正确
     * @param password
     * @param userId
     * @return
     */
    int checkPassword(@Param(value="password")String password,@Param("userId")Integer userId);

    /**
     * 校检邮箱是否可用
     * @param email
     * @param userId
     * @return
     */
    int checkEmailByUserId(@Param(value="email")String email,@Param(value="userId")Integer userId);

    /**
     * 管理员角色登录
     * @param username
     * @param md5PassWord
     * @return
     */
    User selectAdminRoleLogin(String username, String md5PassWord);
}
