package com.shop.dao;

import com.shop.pojo.Category;

import java.util.List;

/**
 * CategoryMapper
 * 分类模块 持久层接口
 * @author Yarn
 * @create 2017/11/9/11:15
 */
public interface CategoryMapper {
    /**
     * 按主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增分类
     * @param record
     * @return
     */
    int insert(Category record);

    /**
     * 选择性新增分类
     * @param record
     * @return
     */
    int insertSelective(Category record);

    /**
     * 通过主键查询分类
     * @param id
     * @return
     */
    Category selectByPrimaryKey(Integer id);

    /**
     * 主键选择更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     * 跟新全部字段
     * @param record
     * @return
     */
    int updateByPrimaryKey(Category record);

    /**
     * 通过父Id查询子类别
     * @param parentId
     * @return
     */
    List<Category> selectCategoryChildrenByParentId(Integer parentId);
}