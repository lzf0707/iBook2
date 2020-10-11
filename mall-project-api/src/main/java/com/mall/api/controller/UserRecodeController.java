package com.mall.api.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.security.SecurityUtils;
import com.mall.entity.BookArticleEntity;
import com.mall.service.BookArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mall.api.security.ApiUser;
import com.mall.common.util.HttpServletRequestUtil;
import com.mall.dto.ResultExecution;
import com.mall.entity.PersonInfoEntity;
import com.mall.enums.StateEnum;
import com.mall.model.Book;
import com.mall.model.BookArticle;
import com.mall.model.UserRecode;
import com.mall.service.PersonInfoService;
import com.mall.service.UserRecodeService;


@Controller
@RequestMapping("/frontend/p")
public class UserRecodeController {

	@Autowired
	public UserRecodeService userRecodeService;
	@Autowired
	public PersonInfoService personInfoService;
	@Autowired
	public BookArticleService bookArticleService;
	
	public UserRecodeController() {
		// TODO Auto-generated constructor stub
	}
	
	//观看领积分
	@RequestMapping(value = "/addUserRecode",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addUserRecode(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		ApiUser apiUser = SecurityUtils.getUser();
		PersonInfoEntity user = personInfoService.getById(apiUser.getuId());
		long bookId = HttpServletRequestUtil.getLong(request, "bookId");
		long articleId = HttpServletRequestUtil.getLong(request, "articleId");
		if(bookId > 0 && articleId > 0 
				&& user != null && user.getUserId() > 0) {
			//判断若章节为积分章节，则不可获取积分
			BookArticleEntity bookArticle = bookArticleService.getOne(new LambdaQueryWrapper<BookArticleEntity>()
					.eq(BookArticleEntity::getArticleId,articleId)
					.eq(BookArticleEntity::getBookId,bookId));
			if(bookArticle.getPoint() > 0){ //积分章节
				modelMap.put("success", false);
				modelMap.put("errMsg", "非免费章节，不可获取积分!");
				return modelMap;
			}
			UserRecode userRecode = new UserRecode();
			userRecode.setPoint(1);
			userRecode.setUserId(user.getUserId());
			Book book = new Book();
			book.setBookId(bookId);
			userRecode.setBook(book);
			BookArticle article = new BookArticle();
			article.setArticleId(articleId);
			userRecode.setArticle(article);
			ResultExecution<UserRecode> rs = userRecodeService.addUserRecode(userRecode);
			if(rs.getState() == StateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);	
				modelMap.put("msg", "积分+" + userRecode.getPoint());
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", rs.getStateInfo());	
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "用户未登录！");	
		}
		return modelMap;
	}
	
	
	@RequestMapping(value = "/getUserRecodeList",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUserRecodeList(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		ApiUser apiUser = SecurityUtils.getUser();
		PersonInfoEntity user = personInfoService.getById(apiUser.getuId());
		int page = HttpServletRequestUtil.getInt(request, "page");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		if(page != -1 && pageSize != -1 
				&& user != null && user.getUserId() > 0) {
			UserRecode userRecode = new UserRecode();
			userRecode.setUserId(user.getUserId());
			String searchInfo = HttpServletRequestUtil.getString(request, "searchInfo");
			if(searchInfo != null) {
				Book book = new Book();
				book.setBookName(searchInfo);
				userRecode.setBook(book);
				BookArticle bookArticle = new BookArticle();
				bookArticle.setArticleName(searchInfo);
				userRecode.setArticle(bookArticle);
			}
			ResultExecution<UserRecode> rs = userRecodeService.getUserRecodeList(userRecode, page, pageSize);
			modelMap.put("userRecodeList", rs.getList());
			modelMap.put("count", rs.getCount());
			modelMap.put("success", true);
			modelMap.put("totalPoint", rs.getTotalPoint());
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or page or 用户未登录！");	
		}
		return modelMap;
	}

}
