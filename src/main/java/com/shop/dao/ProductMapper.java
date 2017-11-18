package com.shop.dao;

import com.shop.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * ProductMapper
 * 产品模块 持久层接口
 * @author Yarn
 * @create 2017/11/9/11:15
 */
public interface ProductMapper {
    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增产品
     * @param record
     * @return
     */
    int insert(Product record);

    /**
     * 按条件查询产品
     * @param record
     * @return
     */
    int insertSelective(Product record);

    /**
     * 通过主键查询产品
     * @param id
     * @return
     */
    Product selectByPrimaryKey(Integer id);

    /**
     * 主键选择更新产品
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Product record);

    /**
     * 按主键跟新全部字段
     * @param record
     * @return
     */
    int updateByPrimaryKey(Product record);

    /**
     * 查询全部产品
     * @return
     */
    List<Product> selectList();

    /**
     * 按名称和产品Id进行查询
     * @param productName
     * @param productId
     * @return
     */
    List<Product> selectByNameAndProductId(@Param("productName") String productName, @Param("productId") Integer productId);

    /**
     * 按名称和类别id进行查询
     * @param productName
     * @param categoryIdList
     * @return
     */
    List<Product> selectByNameAndCategoryIds(@Param("productName") String productName, @Param("categoryIdList") List<Integer> categoryIdList);


}