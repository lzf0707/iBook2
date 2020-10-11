package com.mall.api.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.api.security.ApiUser;
import com.mall.api.security.SecurityUtils;
import com.mall.entity.UserArticleEntity;
import com.mall.service.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mall.common.util.HttpServletRequestUtil;
import com.mall.dto.ResultExecution;
import com.mall.model.BookArticle;
import com.mall.service.BookArticleService;

@Controller
@RequestMapping("/frontend")
public class BookArticleDetailControl {

	@Autowired
	public BookArticleService bookArticleService;
	@Autowired
	public UserArticleService userArticleService;

	public BookArticleDetailControl() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * 获取章节内容详情
	 * @return
	 * */
	@RequestMapping(value = "/listArticleDetailInfo",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listArticleDetailInfo(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//获取前台转过来的章节ID
		long articleId = HttpServletRequestUtil.getLong(request, "articleId");
		int page = HttpServletRequestUtil.getInt(request, "page");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		
		if(articleId != -1 && page != -1 && pageSize != -1) {
			ResultExecution<BookArticle> resultExecution= bookArticleService.getBookArticleById(articleId,page,pageSize);
			BookArticle bookArticle = resultExecution.getE();
			if(bookArticle.getPoint() > 0){
				//判断用户是否登录，登录，则判断用户是否解锁
				if(SecurityUtils.getAuthentication() == null || SecurityUtils.getUser() == null){
					modelMap.put("success", false);
					modelMap.put("errMsg", "非免费章节，请登录后解锁!");
					modelMap.put("bookId", bookArticle.getBookId());
					return modelMap;
				}
				ApiUser apiUser = SecurityUtils.getUser();
				UserArticleEntity userArticleEntity = userArticleService.getOne(new LambdaQueryWrapper<UserArticleEntity>()
						.eq(UserArticleEntity::getArticleId,articleId)
						.eq(UserArticleEntity::getUserId,apiUser.getuId()));
				if(userArticleEntity == null){
					modelMap.put("success", false);
					modelMap.put("errMsg", "非免费章节，请解锁后观看!");
					modelMap.put("bookId", bookArticle.getBookId());
					return modelMap;
				}
			}
			modelMap.put("success", true);
			modelMap.put("bookArticle", bookArticle);
			modelMap.put("count", resultExecution.getCount());
		}else{
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty info");
		}
		return modelMap;
	}
}
