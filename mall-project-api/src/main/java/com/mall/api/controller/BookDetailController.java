package com.mall.api.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.security.ApiUser;
import com.mall.api.security.SecurityUtils;
import com.mall.dto.ResultExecution;
import com.mall.entity.BookArticleEntity;
import com.mall.entity.UserArticleEntity;
import com.mall.entity.UserPointEntity;
import com.mall.entity.UserSubscribeEntity;
import com.mall.enums.StateEnum;
import com.mall.security.dto.SimpleResponse;
import com.mall.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mall.common.util.HttpServletRequestUtil;
import com.mall.model.Book;
import com.mall.model.BookArticle;

@Controller
@RequestMapping("/frontend")
public class BookDetailController {

	@Autowired
	public BookService bookService;
	@Autowired
	public BookArticleService bookArticleService;
	@Autowired
	public UserSubscribeService userSubscribeService;
	@Autowired
	public UserArticleService userArticleService;
	@Autowired
	public UserPointService userPointService;

	public BookDetailController() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * 获取书籍信息
	 * @return
	 * */
	@RequestMapping(value = "listBookDetailPageInfo",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listBookDetailPageInfo(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<>();
		long bookId = HttpServletRequestUtil.getLong(request, "bookId");
		Book book;
		if(bookId != -1) {
			book = bookService.getBookById(bookId);
			modelMap.put("book", book);
			modelMap.put("success", true);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty bookId");
		}
		return modelMap;
	}

	/*
	 * 获取所有章节
	 * @return
	 * */
	@RequestMapping(value = "listArticleByBook",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listArticleByBook(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<>();
		Integer userId = -1;
		if( SecurityUtils.getAuthentication() != null && SecurityUtils.getUser() != null){
			ApiUser apiUser = SecurityUtils.getUser();
			userId = apiUser.getuId();
		}
		long bookId = HttpServletRequestUtil.getLong(request, "bookId");
		String sortFlag = HttpServletRequestUtil.getString(request, "sortFlag");
		if(bookId != -1) {
			BookArticle bookArticle = new BookArticle();
			bookArticle.setBookId(bookId);;
			bookArticle.setEnableStatus(1);
			List<BookArticle> bookArticleList= bookArticleService.getBookArticleList(bookArticle,sortFlag,userId);
			modelMap.put("bookArticleList", bookArticleList);
			modelMap.put("success", true);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty bookId");
		}
		return modelMap;
	}

	/*
	* 判断是否订阅书籍
	* @return
	* */
	@RequestMapping(value = "p/isSubscribe",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<SimpleResponse> isSubscribe(HttpServletRequest request){
		ApiUser apiUser = SecurityUtils.getUser();
		Integer bookId = StringUtils.isBlank(request.getParameter("bookId")) ?
				null : Integer.parseInt(request.getParameter("bookId"));
		if(bookId != null && bookId > 0){
			//判断是否订阅
			UserSubscribeEntity temp = userSubscribeService.getOne(
					new LambdaQueryWrapper<UserSubscribeEntity>()
							.eq(UserSubscribeEntity::getBookId,bookId)
							.eq(UserSubscribeEntity::getUserId,apiUser.getuId()));
			UserSubscribeEntity  userSubscribeEntity = new UserSubscribeEntity();
			if(temp != null && temp.getUserSubscribeId() > 0){
				return ResponseEntity.ok(new SimpleResponse("用户已订阅此书籍!",StateEnum.OK.getState()));
			}else {
				return ResponseEntity.ok(new SimpleResponse("用户未订阅此书籍!",StateEnum.OK.getState()));
			}
		}
		return ResponseEntity.ok(new SimpleResponse("bookId不可为空!", StateEnum.FAILD.getState()));
	}

	/*
	 * 取消订阅
	 * @return
	 * */
	@RequestMapping(value = "p/unSubscribe",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<SimpleResponse> unSubscribe(HttpServletRequest request){
		ApiUser apiUser = SecurityUtils.getUser();
		Integer bookId = StringUtils.isBlank(request.getParameter("bookId")) ?
				null : Integer.parseInt(request.getParameter("bookId"));
		if(bookId != null && bookId > 0){
			//判断是否订阅
			UserSubscribeEntity temp = userSubscribeService.getOne(
					new LambdaQueryWrapper<UserSubscribeEntity>()
							.eq(UserSubscribeEntity::getBookId,bookId)
							.eq(UserSubscribeEntity::getUserId,apiUser.getuId()));
			if(temp == null){
				return ResponseEntity.ok(new SimpleResponse("用户未订阅此书籍!",StateEnum.FAILD.getState()));
			}
			UserSubscribeEntity  userSubscribeEntity = new UserSubscribeEntity();
			userSubscribeEntity.setUserId(apiUser.getuId());
			userSubscribeEntity.setBookId(bookId);
			Boolean rs = userSubscribeService.remove(
					new LambdaQueryWrapper<UserSubscribeEntity>()
							.eq(UserSubscribeEntity::getBookId,bookId)
							.eq(UserSubscribeEntity::getUserId,apiUser.getuId()));
			if(rs){
				return ResponseEntity.ok(new SimpleResponse("取消订阅成功!", StateEnum.OK.getState()));
			}else{
				return ResponseEntity.ok(new SimpleResponse("取消订阅失败!", StateEnum.FAILD.getState()));
			}
		}
		return ResponseEntity.ok(new SimpleResponse("bookId不可为空!", StateEnum.FAILD.getState()));
	}

	/*
	* 书籍订阅
	* @return
	* */
	@RequestMapping(value = "p/subscribe",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<SimpleResponse> subscribe(HttpServletRequest request){
		ApiUser apiUser = SecurityUtils.getUser();
		Integer bookId = StringUtils.isBlank(request.getParameter("bookId")) ?
				null : Integer.parseInt(request.getParameter("bookId"));
		if(bookId != null && bookId > 0){
			//判断是否订阅
			UserSubscribeEntity temp = userSubscribeService.getOne(
					new LambdaQueryWrapper<UserSubscribeEntity>()
							.eq(UserSubscribeEntity::getBookId,bookId)
							.eq(UserSubscribeEntity::getUserId,apiUser.getuId()));
			if(temp != null && temp.getUserSubscribeId() > 0){
				return ResponseEntity.ok(new SimpleResponse("用户已订阅此书籍!",StateEnum.FAILD.getState()));
			}
			UserSubscribeEntity  userSubscribeEntity = new UserSubscribeEntity();
			userSubscribeEntity.setCreateTime(new Date());
			userSubscribeEntity.setUserId(apiUser.getuId());
			userSubscribeEntity.setBookId(bookId);
			Boolean rs = userSubscribeService.save(userSubscribeEntity);
			if(rs){
				return ResponseEntity.ok(new SimpleResponse("订阅成功!", StateEnum.OK.getState()));
			}else{
				return ResponseEntity.ok(new SimpleResponse("订阅失败!", StateEnum.FAILD.getState()));
			}
		}
		return ResponseEntity.ok(new SimpleResponse("bookId不可为空!", StateEnum.FAILD.getState()));
	}

	/**
	 * 获取用户总积分
	 * @return
	 * */
	@RequestMapping(value = "p/getUserAllPoint",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<SimpleResponse> getUserAllPoint(){
		ApiUser apiUser = SecurityUtils.getUser();
		UserPointEntity userPointEntity = userPointService.getOne(new LambdaQueryWrapper<UserPointEntity>()
				.eq(UserPointEntity::getUserId,(long)apiUser.getuId()));
		return ResponseEntity.ok(new SimpleResponse(userPointEntity,StateEnum.OK.getState()));
	}

	/**
	 * 解锁章节：单章 or 所有章节
	 * @return
	 * */
	@RequestMapping(value = "p/unLockArticle",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<SimpleResponse> unLockArticle(HttpServletRequest request){
		ApiUser apiUser = SecurityUtils.getUser();
		String flag = HttpServletRequestUtil.getString(request,"flag");
		Integer articleId = HttpServletRequestUtil.getInt(request,"articleId");
		Integer bookId = HttpServletRequestUtil.getInt(request,"bookId");

		if(!StringUtils.isBlank(flag) && articleId > 0 && bookId > 0){
			ResultExecution<UserArticleEntity> rs = userArticleService.unLockArticle(flag,bookId,articleId,apiUser.getuId());
			if(rs.getState() == StateEnum.OK.getState()){
				return ResponseEntity.ok(new SimpleResponse("章节解锁成功！",StateEnum.OK.getState()));
			}else{
				return ResponseEntity.ok(new SimpleResponse(rs.getStateInfo(),rs.getState()));
			}
		}
		return ResponseEntity.ok(new SimpleResponse("flag为空 或 bookId为空 或 articleId为空！",StateEnum.FAILD.getState()));
	}

	/**
	 * 获取解锁剩余章节所要的积分
	 * @return
	 * */
	@RequestMapping(value = "p/getUnLockArticleTotalPoint",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<SimpleResponse> getUnLockArticleTotalPoint(HttpServletRequest request){
		ApiUser apiUser = SecurityUtils.getUser();
		Integer bookId = HttpServletRequestUtil.getInt(request,"bookId");
		if(bookId > 0){
			int totalPoint = userArticleService.getUnLockArticleTotalPoint(bookId,apiUser.getuId());
			return  ResponseEntity.ok(new SimpleResponse(totalPoint,StateEnum.OK.getState()));
		}
		return ResponseEntity.ok(new SimpleResponse("bookId为空！",StateEnum.FAILD.getState()));
	}

	/**
	 * 判断章节是否免费阅读
	 * @return
	 * */
	@RequestMapping(value = "getArticleStatus",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<SimpleResponse> getArticleStatus(HttpServletRequest request){
		Integer articleId = HttpServletRequestUtil.getInt(request,"articleId");
		if(articleId > 0){
			BookArticleEntity bookArticle = bookArticleService.getById(articleId);
			if(bookArticle.getPoint() > 0){
				//判断用户是否登录，登录，则判断用户是否解锁
				if(SecurityUtils.getAuthentication() == null || SecurityUtils.getUser() == null){
					return ResponseEntity.ok(new SimpleResponse("非免费章节，请登录后解锁!",StateEnum.FAILD.getState()));
				}
				ApiUser apiUser = SecurityUtils.getUser();
				UserArticleEntity userArticleEntity = userArticleService.getOne(new LambdaQueryWrapper<UserArticleEntity>()
						.eq(UserArticleEntity::getArticleId,articleId)
						.eq(UserArticleEntity::getUserId,apiUser.getuId()));
				if(userArticleEntity == null){
					return ResponseEntity.ok(new SimpleResponse("非免费章节，请解锁后观看!",StateEnum.FAILD.getState()));
				}
			}
			return ResponseEntity.ok(new SimpleResponse("章节已解锁!",StateEnum.OK.getState()));
		}
		return ResponseEntity.ok(new SimpleResponse("articleId为空！",StateEnum.FAILD.getState()));
	}
}
