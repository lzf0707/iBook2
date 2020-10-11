package com.mall.dao;

import com.mall.entity.BookCategoryEntity;
import com.mall.model.BookCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:26:53
 */
public interface BookCategoryDao extends BaseMapper<BookCategoryEntity> {
	
	/*
	 * 类别添加
	 * @return
	 * */
	int addBookCategory(BookCategory bookCategory);
	
	/*
	 * 类别修改
	 * @return
	 * */
	int updateBookCategory(BookCategory bookCategory);
	
	/*
	 * 通过Id删除类别
	 * @return
	 * */
	int deleteBookCategory(long bookCategoryId);
	
	/*
	 * 批量删除类别
	 * @return
	 * */
	int batchDeleteBookCategory(List<Long> list);
	
	/*
	 * 类别查询
	 * @return
	 * */
	int queryBookCategoryListCount(@Param("bookCategoryCondition") BookCategory bookCategory,
			@Param("pageIndex") int pageIndex,@Param("pageSize") int pageSize);

	
	/*
	 * 类别查询
	 * @return
	 * */
	BookCategory queryBookCategoryById(long bookCategoryId);
	
	/*
	 * 根据id组查询类别
	 * @return
	 * */
	List<BookCategory> queryBookCategoryByIds(List<Long> list);
	
	/*
	 * 类别查询
	 * @return
	 * */
	IPage<BookCategory> queryBookCategoryList(Page<BookCategory> page,@Param("bookCategoryCondition") BookCategory bookCategory);
}
