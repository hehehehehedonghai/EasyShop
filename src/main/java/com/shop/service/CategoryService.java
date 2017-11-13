package com.shop.service;

import com.shop.common.ServerResponse;
import com.shop.pojo.Category;

import java.util.List;

/**
 * CategoryService
 * 后台商品管理接口
 * @author Yarn
 * @create 2017/11/13/10:36
 */
public interface CategoryService {
    ServerResponse<String> addCategory(String categoryName, int parentId);

    ServerResponse<String> setCategoryName(Integer categoryId, String categoryName);

    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    ServerResponse<List<Category>> selectCategoryAndChildrenById(Integer categoryId);
}
