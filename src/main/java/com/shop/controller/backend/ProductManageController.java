package com.shop.controller.backend;

import com.shop.common.Const;
import com.shop.common.ResponseCode;
import com.shop.common.ServerResponse;
import com.shop.pojo.Product;
import com.shop.pojo.User;
import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * ProductManageController
 * 后台产品管理模块
 * @author Yarn
 * @create 2017/11/14/11:26
 */
@RestController
@RequestMapping("/manage/product")
public class ProductManageController {

    @Autowired
    private ProductService productService;

    @RequestMapping("save.do")
    @ResponseBody
    public ServerResponse productSave(HttpSession session, Product product){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        //校检是否为管理员角色
        ServerResponse serverResponse = this.checkAdminRole(user);
        if(serverResponse.isSuccess()){
            //填充我们增加产品的业务逻辑
            return productService.saveOrUpdateProduct(product);
        }else{
            return serverResponse;
        }
    }

    @RequestMapping("set_sale_status.do")
    @ResponseBody
    public ServerResponse setSaleStatus(HttpSession session, Integer productId,Integer status){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        //校检是否为管理员角色
        ServerResponse serverResponse = this.checkAdminRole(user);
        if(serverResponse.isSuccess()){
            //填充我们增加产品的业务逻辑
            return productService.setSaleStatus(productId,status);
        }else{
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
