package com.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dto.ResultExecution;
import com.mall.entity.BookCategoryEntity;
import com.mall.model.BookCategory;

/**
 * 
 *
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:36:25
 */
public interface BookCategoryService extends IService<BookCategoryEntity> {
public static final String SCLISTKEY = "shopCategoryList";
	
	/*
	 * 根据查询条件获取类别列表
	 * @return
	 * */
	ResultExecution<BookCategory> getBookCategoryList(BookCategory bookCategory,Page<BookCategory> page);
	
		
	/*
	 * 根据id查询类别
	 * @return
	 * */
	BookCategory getBookCategoryById(Long bookCategoryId);
}

