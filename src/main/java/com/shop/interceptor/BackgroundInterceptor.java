package com.shop.interceptor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shop.common.Const;
import com.shop.common.ResponseCode;
import com.shop.common.ServerResponse;
import com.shop.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * BackgroundInterceptor
 * 后台管理 拦截器
 * 对后台商品进行操作之前
 * 要校检当前登录角色必须为管理员角色
 * @author Yarn
 * @create 2017/11/15/8:50
 */
public class BackgroundInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        User loginUser = (User) session.getAttribute(Const.CURRENT_USER);
        // 用户未登录
        if (loginUser == null) {
            ServerResponse<Object> msg =
                    ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
            Gson gson = new GsonBuilder().create();
            String json = gson.toJson(msg);
            httpServletResponse.getWriter().write(json);
            return false;
        }
        // 用户的角色为普通角色
        if (loginUser.getRole().intValue() == Const.Role.ROLE_CUSTOMER) {
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
