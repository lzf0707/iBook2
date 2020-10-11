package com.mall.api.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.mall.api.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mall.api.security.ApiUser;
import com.mall.common.util.HttpServletRequestUtil;
import com.mall.common.util.PageParam;
import com.mall.dto.ResultExecution;
import com.mall.model.UserSignin;
import com.mall.enums.StateEnum;
import com.mall.service.UserSigninService;

@Controller
@RequestMapping("/frontend/p")
public class UserSigninController {

	@Autowired
	public UserSigninService userSigninService;
	
	public UserSigninController() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * 签到
	 * */
	@RequestMapping(value = "/signIn",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> signIn(){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		ApiUser apiUser = SecurityUtils.getUser();
		if(apiUser != null && apiUser.getuId() > 0) {
			UserSignin userSignin = new UserSignin();
			userSignin.setPoint(1);
			userSignin.setUserId(apiUser.getuId());
			ResultExecution<UserSignin> rs = userSigninService.addUserSignin(userSignin);
			if(rs.getState() == StateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);	
				modelMap.put("msg", "签到成功");
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

	
	/*
	 * 签到记录查询
	 * */
	@RequestMapping(value = "/getSigninRecode",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getSigninRecode(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		ApiUser apiUser = SecurityUtils.getUser();
		int page = HttpServletRequestUtil.getInt(request, "page");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		if(page != -1 && pageSize != -1 
				&& apiUser != null && apiUser.getuId() > 0) {
			UserSignin userSignin = new UserSignin();
			userSignin.setUserId(apiUser.getuId());
			String createTime = HttpServletRequestUtil.getString(request, "createTime");
			if(createTime != null) {
				userSignin.setCreateTime(createTime);
			}
			PageParam<UserSignin> pageParam = new PageParam<UserSignin>();
			pageParam.setCurrent(page);
			pageParam.setSize(pageSize);
			ResultExecution<UserSignin> rs = userSigninService.getUserSigninList(userSignin, pageParam);
			modelMap.put("signinList", rs.getList());
			modelMap.put("count", rs.getCount());
			modelMap.put("success", true);
			modelMap.put("totalPoint", rs.getTotalPoint());
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or page or 用户未登录！");	
		}
		return modelMap;
	}
	
	
	/*
	 * 获取今日签到记录
	 * @return
	 * */
	@RequestMapping(value = "/getTodaySignin",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getTodaySignin(){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		ApiUser apiUser = SecurityUtils.getUser();
		if(apiUser != null && apiUser.getuId() > 0) {			
			UserSignin userSignin = userSigninService.getUsersigninByCTime(apiUser.getuId());
			if(userSignin != null) {
				modelMap.put("success", true);
				modelMap.put("msg", "已签到");
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "未签到");
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "用户未登录！");	
		}
		return modelMap;
	}
}
