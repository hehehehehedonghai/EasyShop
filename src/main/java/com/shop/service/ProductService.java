package com.shop.service;

import com.shop.common.ServerResponse;
import com.shop.pojo.Product; /**
 * ProductService
 *
 * @author Yarn
 * @create 2017/11/14/11:33
 */
public interface ProductService {
    /**
     * 保存或者更新 商品信息
     * @param product
     * @return
     */
    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse<String> setSaleStatus(Integer productId, Integer status);
}
