package com.mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.exception.OperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mall.common.util.PageCalculator;
import com.mall.dao.BookDao;
import com.mall.dto.ResultExecution;
import com.mall.entity.BookEntity;
import com.mall.enums.StateEnum;
import com.mall.model.Book;
import com.mall.service.BookService;

@Service
public class BookServiceImpl extends ServiceImpl<BookDao, BookEntity> implements BookService {
	@Autowired
	private BookDao bookDao;
	
	@Override
	public Book getBookById(Long bookId) {
		// TODO Auto-generated method stub
		return bookDao.queryBookById(bookId);
	}

	@Override
	public ResultExecution<Book> getBookList(Book bookCondition, int page, int pageSize,int updateFlag,int publishFlag) {
		// TODO Auto-generated method stub
		//将页码转为行码
		int rowIndex = PageCalculator.calculateRowIndex(page, pageSize);
		//依据条件查询
		List<Book> list = bookDao.queryBookList(bookCondition, rowIndex, pageSize,updateFlag,publishFlag);
		int count = bookDao.queryBookCount(bookCondition);
		ResultExecution<Book> resultExecution = new ResultExecution<Book>();
		if(list != null) {
			resultExecution.setList(list);
			resultExecution.setCount(count);
		}else {
			resultExecution.setState(StateEnum.INNER_ERROR.getState());
		}
		return resultExecution;
	}

	@Override
	public List<JSONObject> getIndexRecommend() {
		List<JSONObject> list = new ArrayList<>();
		JSONArray jArr = new JSONArray();
		JSONObject jObj1 = new JSONObject();
		jObj1.put("title","订阅 Top 排行");
		jObj1.put("flag",1);
		jArr.add(jObj1);
		JSONObject jObj2 = new JSONObject();
		jObj2.put("title","浏览 Top 排行");
		jObj2.put("flag",2);
		jArr.add(jObj2);
		JSONObject jObj3 = new JSONObject();
		jObj3.put("title","热销 Top 排行");
		jObj3.put("flag",3);
		jArr.add(jObj3);

		try {
			for (Object jObj:jArr) {
				JSONObject j = (JSONObject) jObj;
				String title = j.getString("title");
				Integer flag = j.getInteger("flag");
				List<Book> bookList = bookDao.selectIndexRecommend(flag);
				JSONObject obj = new JSONObject();
				obj.put("title",title);
				obj.put("bookList",bookList);
				list.add(obj);
			}
		}catch (Exception e){
			throw new OperationException("获取首页推荐列表失败-" + e.getMessage());
		}
		return list;
	}

}