package com.shop.controller.backend;


import com.google.common.collect.Maps;
import com.shop.common.Const;
import com.shop.common.ResponseCode;
import com.shop.common.ServerResponse;
import com.shop.pojo.Product;
import com.shop.pojo.User;
import com.shop.service.FileService;
import com.shop.service.ProductService;
import com.shop.utils.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

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
    @Autowired
    private FileService fileService;

    @RequestMapping("save.do")
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

    @RequestMapping("detail.do")
    public ServerResponse getDetail(HttpSession session, Integer productId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        //校检是否为管理员角色
        ServerResponse serverResponse = this.checkAdminRole(user);
        if(serverResponse.isSuccess()){
            return productService.manageProductDetail(productId);
        }else{
            return serverResponse;
        }
    }

    /**
     * 商品分页list
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("list.do")
    public ServerResponse getList(HttpSession session,
                                  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        //校检是否为管理员角色
        ServerResponse serverResponse = this.checkAdminRole(user);
        if(serverResponse.isSuccess()){
            return productService.getProductList(pageNum, pageSize);
        }else{
            return serverResponse;
        }
    }

    @RequestMapping("search.do")
    public ServerResponse productSearch(HttpSession session,
                                  String productName,
                                  Integer productId,
                                  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        //校检是否为管理员角色
        ServerResponse serverResponse = this.checkAdminRole(user);
        if(serverResponse.isSuccess()){
            return productService.searchProduct(productName,productId,pageNum,pageSize);
        }else{
            return serverResponse;
        }
    }

    @RequestMapping("upload.do")
    public ServerResponse upload(HttpSession session,
                                 MultipartFile file, HttpServletRequest request){

        User user = (User)session.getAttribute(Const.CURRENT_USER);
        //校检是否为管理员角色
        ServerResponse serverResponse = this.checkAdminRole(user);
        if(serverResponse.isSuccess()){
            String path = request.getSession().getServletContext().getRealPath("upload");
            String fileName = fileService.upload(file, path);
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + fileName;
            Map fileMap = Maps.newHashMap();
            fileMap.put("uri", fileName);
            fileMap.put("url", url);
            return ServerResponse.createBySuccess(fileMap);
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
