package com.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.entity.BookEntity;
import com.mall.model.Book;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:26:53
 */
public interface BookDao extends BaseMapper<BookEntity> {
	/*
	 * 添加书籍
	 * @return
	 * */
	int  insertBook(Book book);
	
	/*
	 * 删除书籍
	 * @return
	 * */
	int deleteBook(Long bookId);
	
	/*
	 * 批量删除
	 * @return
	 * */
	int batchDeleteBook(List<Long> list);
	
	/*
	 * 更新书籍
	 * @return
	 * */
	int updateBook(Book book);
	
	/*
	 * 获取书籍列表
	 * @return
	 * */
	List<Book> queryBookList(@Param("bookCondition") Book bookCondition,
			@Param("rowIndex") Integer rowIndex,@Param("pageSize") Integer pageSize,
			@Param("updateFlag") Integer updateFlag,@Param("publishFlag") Integer publishFlag);
	
	/*
	 * 更具ID获取书籍
	 * @return
	 * */
	Book queryBookById(Long bookId);
	
	/*
	 * 获取BookList总数
	 * @return
	 * */
	int queryBookCount(@Param("bookCondition") Book bookCondition);

	/**
	 * 首页推荐栏
	 * @return
	 * */
	List<Book> selectIndexRecommend(@Param("flag") Integer flag);
}
