package com.shop.controller.portal;

import com.shop.common.Const;
import com.shop.common.ResponseCode;
import com.shop.common.ServerResponse;
import com.shop.pojo.User;
import com.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * CartController
 * 购物车模块
 * @author Yarn
 * @create 2017/11/15/10:25
 */
@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "add.do")
    public ServerResponse addCart(HttpSession session,Integer count, Integer productId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        ServerResponse serverResponse = this.checkAdminRole(user);
        if (serverResponse.isSuccess()) {
            return cartService.add(user.getId(), productId, count);
        } else {
            return serverResponse;
        }
    }


    /**
     * 检查用户类型是否为管理员
     * @param user
     * @return
     */
    private ServerResponse checkAdminRole(User user){
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        //校检是否是管理员角色
        if (user.getRole().intValue() == Const.Role.ROLE_ADMIN){
            return ServerResponse.createBySuccess();
        } else {
            return ServerResponse.createByErrorMessage("不是管理员 无权操作");
        }
    }
}
