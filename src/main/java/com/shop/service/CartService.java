package com.shop.service;

import com.shop.common.ServerResponse;

/**
 * CartService
 * 购物车业务层 接口
 * @author Yarn
 * @create 2017/11/15/10:30
 */
public interface CartService {

    ServerResponse add(Integer id, Integer productId, Integer count);
}
