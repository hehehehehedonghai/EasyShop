package com.shop.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 *
 * 前台用户模块单元测试
 * UserControllerTest
 * 使用springTest单元测试
 * @author Yarn
 * @create 2017年11月16日 13:22:10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml",
        "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    WebApplicationContext ctx;

    /**
     * 虚拟mvc请求
     */
    MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }


    /**
     * 用户登录单元测试
     * @throws Exception
     */
    @Test
    public void login() throws Exception {
        String username = "admin";
        String password = "123123";
        MvcResult result = mockMvc.perform(
                 MockMvcRequestBuilders.post("/user/login.do")
                .param("username", username)
                .param("password", password))
                .andReturn();
        //拿到响应字符串
        String content = result.getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    public void getUserInfo() throws Exception {
        System.out.println("测你mmp, 不测了");
    }

    @Test
    public void register() throws Exception {
    }

    @Test
    public void logout() throws Exception {
    }

    @Test
    public void checkUserNameOrEmail() throws Exception {
    }

    @Test
    public void getQuestion() throws Exception {
    }

    @Test
    public void getQuestionAnswer() throws Exception {
    }

    @Test
    public void forgetResetPassword() throws Exception {
    }

    @Test
    public void resetPassword() throws Exception {
    }

    @Test
    public void updateInformation() throws Exception {
    }

    @Test
    public void getInformation() throws Exception {
    }

}