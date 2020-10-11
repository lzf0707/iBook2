package com.mall.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mall.common.util.HttpServletRequestUtil;
import com.mall.common.util.PageParam;
import com.mall.dto.ResultExecution;
import com.mall.model.Area;
import com.mall.model.Book;
import com.mall.model.BookCategory;
import com.mall.service.AreaService;
import com.mall.service.BookCategoryService;
import com.mall.service.BookService;

@Controller
@RequestMapping("/frontend")
public class BookListController {

	@Autowired
	public BookCategoryService bookCategoryService;
	@Autowired
	public AreaService areaService;
	@Autowired
	public BookService bookService;
	
	public BookListController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/listShopsPageInfo",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> listShopsPageInfo(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String, Object>();
		long parentId = HttpServletRequestUtil.getLong(request, "parentId");
		//类别ID不传值默认为-1
		List<BookCategory> bookCategoryList = null;
		try {
			BookCategory bookCategory = new BookCategory();
			BookCategory parent = new BookCategory();
			parent.setbookCategoryId(parentId);
			bookCategory.setParent(parent);
			PageParam<BookCategory> pageParam = new PageParam<BookCategory>();
			pageParam.setCurrent(1);
			pageParam.setSize(999);
			ResultExecution<BookCategory> rs = bookCategoryService.getBookCategoryList(bookCategory,pageParam);
			bookCategoryList = rs.getList();
		} catch (Exception e) {
			// TODO: handle exception
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		modelMap.put("bookCategoryList", bookCategoryList);
		
		List<Area> areaList = null;
		try {
			areaList = areaService.getAreaList();
			modelMap.put("success", true);
			modelMap.put("areaList", areaList);
		} catch (Exception e) {
			// TODO: handle exception
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}		
		return modelMap;
	}
	
	/*
	 * 获取指定条件下得书籍列表
	 * @return
	 * */
	@RequestMapping(value = "/listbooks",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listbooks(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		Integer updateFlag = HttpServletRequestUtil.getInt(request, "updateFlag"); 
		Integer publishFlag = HttpServletRequestUtil.getInt(request, "publishFlag"); 
		if(updateFlag != -1L) {
			updateFlag = 1;
		}
		if(publishFlag != -1L) {
			publishFlag = 1;
		}
		
		Integer endStatus = HttpServletRequestUtil.getInt(request, "endStatus");
		//非空
		if((pageIndex >-1) && (pageSize > -1)) {
			//获取一级类别ID
			long parentId = HttpServletRequestUtil.getLong(request, "parentId");
			//获取二级类别ID
			long bookCategoryId = HttpServletRequestUtil.getLong(request, "bookCategoryId");
			//获取区域Id
			int areaId = HttpServletRequestUtil.getInt(request, "areaId");
			String bookName = HttpServletRequestUtil.getString(request, "bookName");
			Book bookCondition = compactBookConditionSearch(parentId, bookCategoryId, areaId, bookName,endStatus);
			ResultExecution<Book> resultExecution = bookService.getBookList(bookCondition, pageIndex, pageSize,updateFlag,publishFlag);
			modelMap.put("bookList", resultExecution.getList());
			modelMap.put("count", resultExecution.getCount());
			modelMap.put("success", true);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex");
		}
		return modelMap;
	}
	
	public Book compactBookConditionSearch(long parentId,long bookCategoryId,
			int areaId,String bookName,Integer endStatus) {
		Book bookCondition = new Book();
		
		//查询某个一节类别下面得所有类别
		if(parentId != -1L) {
			BookCategory bookCategory = new BookCategory();
			BookCategory parent = new BookCategory();
			parent.setbookCategoryId(parentId);
			bookCategory.setParent(parent);
			bookCondition.setBookCategory(bookCategory);
		}
		
		//查询某个二级类别
		if(bookCategoryId != -1L) {
			BookCategory bookCategory = new BookCategory();
			bookCategory.setbookCategoryId(bookCategoryId);
			bookCondition.setBookCategory(bookCategory);
		}
		
		if(areaId != -1L) {
			Area area = new Area();
			area.setAreaId((long)areaId);
			bookCondition.setArea(area);
		}
		
		if(bookName != null) {
			bookCondition.setBookName(bookName);
		}
		
		if(endStatus != -1L) {
			bookCondition.setEndStatus(endStatus);
		}
		//前端展示得书籍都是审核通过的
		bookCondition.setEnableStatus(1);
		return bookCondition;
	}
}
