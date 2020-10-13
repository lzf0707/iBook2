package com.mall.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dto.ResultExecution;
import com.mall.entity.BookEntity;
import com.mall.model.Book;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 
 *
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:36:25
 */
@CacheConfig(cacheNames = "bookService")
public interface BookService extends IService<BookEntity> {
	/*
	 * 通过ID获取书籍
	 *  @return
	 * */
	@Cacheable
	Book getBookById(Long bookId);
	
	/*
	 * 根据条件分页返回书籍列表
	 *  @return
	 * */
	@Cacheable
	ResultExecution<Book> getBookList(Book bookCondition,
			int page,int pageSize,
			int updateFlag,int publishFlag);

	List<JSONObject> getIndexRecommend();
}

