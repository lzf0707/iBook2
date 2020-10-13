package com.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dto.ResultExecution;
import com.mall.entity.BookCategoryEntity;
import com.mall.model.BookCategory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * 
 *
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:36:25
 */
@CacheConfig(cacheNames = "bookCategoryService")
public interface BookCategoryService extends IService<BookCategoryEntity> {
	
	/*
	 * 根据查询条件获取类别列表
	 * @return
	 * */
	@Cacheable
	ResultExecution<BookCategory> getBookCategoryList(BookCategory bookCategory,Page<BookCategory> page);
	
		
	/*
	 * 根据id查询类别
	 * @return
	 * */
	@Cacheable
	BookCategory getBookCategoryById(Long bookCategoryId);
}

