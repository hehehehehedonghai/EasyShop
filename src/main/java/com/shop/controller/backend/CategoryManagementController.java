package com.shop.controller.backend;

import com.shop.common.Const;
import com.shop.common.ResponseCode;
import com.shop.common.ServerResponse;
import com.shop.pojo.User;
import com.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
/**
 * CategoryManagementController
 * 后台商品分类管理
 * @author Yarn
 * @create 2017/11/13/10:00
 */
@RestController
@RequestMapping("/manage/category")
public class CategoryManagementController {

    @Autowired
    private CategoryService categoryService;
    /**
     * 添加商品分类
     * @param session
     * @param categoryName 商品名字
     * @param parentId
     * @return
     */
    @RequestMapping("add_Category.do")
    public ServerResponse<String> addCategory(HttpSession session, String categoryName,
                                              @RequestParam(value = "parentId", defaultValue = "0") Integer parentId ){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        //校检是否是管理员角色
        ServerResponse serverResponse = this.checkAdminRole(user);
        if (serverResponse.isSuccess()) {
            return categoryService.addCategory(categoryName, parentId);
        } else {
            return serverResponse;
        }

    }

    /**
     *
     * @param session
     * @param categoryId
     * @param categoryName
     * @return
     */
    @RequestMapping("set_category_name.do")
    public ServerResponse<String> setCategoryName(HttpSession session, Integer categoryId, String categoryName){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        //校检是否是管理员角色
        ServerResponse serverResponse = this.checkAdminRole(user);
        if (serverResponse.isSuccess()) {
            return categoryService.setCategoryName(categoryId, categoryName);
        } else {
            return serverResponse;
        }
    }

    @RequestMapping("get_category.do")
    public ServerResponse getChildrenParallelCategory(HttpSession session, @RequestParam(value = "categoryId", defaultValue = "0")Integer categoryId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        //校检是否是管理员角色
        ServerResponse serverResponse = this.checkAdminRole(user);
        if (serverResponse.isSuccess()) {
            return categoryService.getChildrenParallelCategory(categoryId);
        } else {
            return serverResponse;
        }
    }

    @RequestMapping("get_deep_category.do")
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session, @RequestParam(value = "categoryId", defaultValue = "0")Integer categoryId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        //校检是否是管理员角色
        ServerResponse serverResponse = this.checkAdminRole(user);
        if (serverResponse.isSuccess()) {
            return categoryService.selectCategoryAndChildrenById(categoryId);
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
