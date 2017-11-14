package com.shop.service.impl;

import com.shop.common.ResponseCode;
import com.shop.common.ServerResponse;
import com.shop.dao.ProductMapper;
import com.shop.pojo.Product;
import com.shop.service.ProductService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProductServiceImpl
 *
 * @author Yarn
 * @create 2017/11/14/11:33
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ServerResponse saveOrUpdateProduct(Product product) {
        if (product != null) {
            if (StringUtils.isNotBlank(product.getSubImages())) {
                String[] subImages = product.getSubImages().split(",");
                if (subImages.length > 0) {
                    product.setMainImage(subImages[0]);
                }
            }

            if (product.getId() != null) {
                //如果产品id不等于null 则是修改产品状态
                int rowCount = productMapper.updateByPrimaryKey(product);
                if (rowCount == 0) {
                    return ServerResponse.createByErrorMessage("更新产品失败,请重试");
                }
                return ServerResponse.createBySuccess("更新产品成功");
            } else {
                //产品id等于null 则是新增产品
                int rowCount = productMapper.insert(product);
                if (rowCount == 0) {
                    return ServerResponse.createByErrorMessage("新增产品失败,请重试");
                }
                return ServerResponse.createBySuccess("新增产品成功");
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新产品参数错误");
    }

    @Override
    public ServerResponse<String> setSaleStatus(Integer productId, Integer status) {
        //校检参数
        if (productId == null || status == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if (rowCount == 0) {
            return ServerResponse.createByErrorMessage("更新产品销售状态失败,请重试");
        }
        return ServerResponse.createBySuccess("更新产品销售状态成功");
    }
}
