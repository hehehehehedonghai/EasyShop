package com.shop.dao;

import com.shop.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * CartMapper
 * 购物车模块 持久层接口
 * @author Yarn
 * @create 2017/11/9/11:15
 */
public interface CartMapper {
    /**
     * 按照id删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增记录
     * @param record
     * @return
     */
    int insert(Cart record);

    /**
     * 有条件性的进行更新
     * @param record
     * @return
     */
    int insertSelective(Cart record);

    /**
     * 根据主键进行查询
     * @param id
     * @return
     */
    Cart selectByPrimaryKey(Integer id);

    /**
     * 主键选择更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Cart record);

    /**
     * 根据主键根据全部字段
     * @param record
     * @return
     */
    int updateByPrimaryKey(Cart record);

    /**
     * 按照用户id和产品Id查询购物车
     * @param userId
     * @param productId
     * @return
     */
    Cart selectCartByUserIdProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);

    /**
     * 按照用户id查询购物车集合
     * @param userId
     * @return
     */
    List<Cart> selectCartByUserId(Integer userId);

    /**
     * 通过用户Id选择购物车产品检查状态
     * @param userId
     * @return
     */
    int selectCartProductCheckedStatusByUserId(Integer userId);

    /**
     * 删除用户Id产品Id
     * @param userId
     * @param productIdList
     * @return
     */
    int deleteByUserIdProductIds(@Param("userId") Integer userId, @Param("productIdList") List<String> productIdList);

    /**
     * 选中或未被选中的产品
     * @param userId
     * @param productId
     * @param checked
     * @return
     */
    int checkedOrUncheckedProduct(@Param("userId") Integer userId, @Param("productId") Integer productId, @Param("checked") Integer checked);

    /**
     * 选择购物车产品数
     * @param userId
     * @return
     */
    int selectCartProductCount(@Param("userId") Integer userId);

    /**
     * 根据用户Id选择检查购物车
     * @param userId
     * @return
     */
    List<Cart> selectCheckedCartByUserId(Integer userId);


}