package com.shop.service.impl;

import com.shop.common.ServerResponse;
import com.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CartServiceImpl
 *
 * @author Yarn
 * @create 2017/11/15/10:31
 */
@Service
public class CartServiceImpl implements CartService {

   // @Autowired


    @Override
    public ServerResponse add(Integer id, Integer productId, Integer count) {
        return null;
    }
}
