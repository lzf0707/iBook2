package com.mall.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import com.mall.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mall.model.HeadLine;
import com.mall.service.BookCategoryService;
import com.mall.service.HeadLineService;

@Controller
@RequestMapping("/frontend")
public class MainPageController {

	@Autowired
	public BookCategoryService bookCategoryService;
	@Autowired
	public HeadLineService headLineService;
	@Autowired
	public BookService bookService;

	public MainPageController() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * 初始化前端展示系统主页信息，包括获取一级店铺类别列表以及头条列表
	 * @return
	 * */
	@RequestMapping(value = "/listMainMageInfo",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> listMainMageInfo(){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<HeadLine> headLineList = new ArrayList<HeadLine>();
		try {
			HeadLine headLine = new HeadLine();
			headLine.setEnableStatus(1);
			headLineList = headLineService.getHeadLineList(headLine);
			modelMap.put("headLineList", headLineList);
		} catch (Exception e) {
			// TODO: handle exception
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		
//		List<BookCategory> bookCategoryList = new ArrayList<BookCategory>();
//		try {
//			BookCategory bookCategory = new BookCategory();
//			BookCategory parent = new BookCategory();
//			parent.setbookCategoryId((long)-1);
//			bookCategory.setParent(parent);
//			PageParam<BookCategory> page = new PageParam<BookCategory>();
//			page.setCurrent(1);
//			page.setSize(999);
//			//获取一级店铺类别列表
//			ResultExecution<BookCategory> rs =  bookCategoryService.getBookCategoryList(bookCategory,page);
//			bookCategoryList = rs.getList();
//			modelMap.put("bookCategoryList", bookCategoryList);
//		} catch (Exception e) {
//			// TODO: handle exception
//			modelMap.put("success", false);
//			modelMap.put("errMsg", e.getMessage());
//			return modelMap;
//		}
		//首页推荐专栏列表获取
		List<JSONObject> list = bookService.getIndexRecommend();
		modelMap.put("list", list);

		modelMap.put("success", true);
		return modelMap;
	}
}
