package com.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.BookCategoryDao;
import com.mall.dto.ResultExecution;
import com.mall.entity.BookCategoryEntity;
import com.mall.model.BookCategory;
import com.mall.service.BookCategoryService;


@Service
public class BookCategoryServiceImpl extends ServiceImpl<BookCategoryDao, BookCategoryEntity> implements BookCategoryService {
	@Autowired
	private BookCategoryDao bookCategoryDao;

	@Override
	public ResultExecution<BookCategory> getBookCategoryList(BookCategory bookCategory, Page<BookCategory> page) {
		// TODO Auto-generated method stub
		ResultExecution<BookCategory> rs = new ResultExecution<BookCategory>();
		IPage<BookCategory> bookCategoryList = bookCategoryDao.queryBookCategoryList(page,bookCategory);
		int count = (int)bookCategoryList.getTotal();
		rs.setList(bookCategoryList.getRecords());
		//rs.setCount(count);
		rs.setCount((int) bookCategoryList.getTotal());
		return rs;
	}


	@Override
	public BookCategory getBookCategoryById(Long bookCategoryId) {
		// TODO Auto-generated method stub
		return bookCategoryDao.queryBookCategoryById(bookCategoryId);
	}
	
	

   

}