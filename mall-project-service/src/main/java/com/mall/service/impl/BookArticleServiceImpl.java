package com.mall.service.impl;

import java.util.List;

import com.mall.common.exception.OperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.util.PageCalculator;
import com.mall.dao.BookArticleDao;
import com.mall.dto.ResultExecution;
import com.mall.entity.BookArticleEntity;
import com.mall.enums.StateEnum;
import com.mall.model.BookArticle;
import com.mall.service.BookArticleService;


@Service
public class BookArticleServiceImpl extends ServiceImpl<BookArticleDao, BookArticleEntity> implements BookArticleService {
	@Autowired
	private BookArticleDao bookArticleDao;

	public BookArticleServiceImpl() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public List<BookArticle> getBookArticleList(BookArticle bookArticle,String sortFlag,Integer userId) {
		// TODO Auto-generated method stub
		List<BookArticle> list;
		try {
			list = bookArticleDao.queryArticleList(bookArticle,sortFlag,userId);
			return list;
		}catch (Exception e){
			throw new OperationException("获取书籍章节里列表失败-" + e.getMessage());
		}
	}

	@Override
	public ResultExecution<BookArticle> getBookArticleById(long articleId,int page,int pageSize) {
		// TODO Auto-generated method stub
		//将页码转为行码
		int rowIndex = PageCalculator.calculateRowIndex(page, pageSize);
		int count = bookArticleDao.queryArticleCountById(articleId, rowIndex, pageSize);
		BookArticle bookArticle = bookArticleDao.queryArticleById(articleId,rowIndex,pageSize);
		ResultExecution<BookArticle> resultExecution = new ResultExecution<BookArticle>();
		if(bookArticle != null) {
			//获取上一章节 下一章节ID
			BookArticle lastNextArticle = bookArticleDao.queryLastNextArticleId(articleId);
			bookArticle.setLastId(lastNextArticle.getLastId());
			bookArticle.setNextId(lastNextArticle.getNextId());		
			resultExecution.setE(bookArticle);
			resultExecution.setCount(count);
		}else {
			resultExecution.setState(StateEnum.INNER_ERROR.getState());
		}
		return resultExecution;
	}

}