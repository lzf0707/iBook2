package com.mall.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/frontend")
public class FrontendController {

	public FrontendController() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 首页路由
	 * @return
	 * */
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		return "frontend/index";
	}
	
	/*
	 * 书籍列表页路由
	 * @return
	 * */
	@RequestMapping(value = "/booklist",method = RequestMethod.GET)
	public String booklist(HttpServletRequest request) {
		return "frontend/booklist";
	}
	
	//书籍列表详情页
	@RequestMapping(value = "/bookdetail",method = RequestMethod.GET)
	public String bookdetail(HttpServletRequest request) {
		return "frontend/bookdetail";
	}
	
	//章节详情
	@RequestMapping(value = "/articledetail",method = RequestMethod.GET)
	public String articledetail(HttpServletRequest request) {
		return "frontend/articledetail";
	}
	
	//本地账号绑定
	@RequestMapping(value = "/accountBind",method = RequestMethod.GET)
	public String bindLocalAuth(HttpServletRequest request) {
		return "frontend/accountBind";
	}
	
	//登录界面
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		return "frontend/login";
	}
	
	//修改密码
	@RequestMapping(value = "/changePwd",method = RequestMethod.GET)
	public String changePwd(HttpServletRequest request) {
		return "frontend/changepsw";
	}
	
	//签到列表
	@RequestMapping(value = "/signinList",method = RequestMethod.GET)
	public String signinList(HttpServletRequest request) {
		return "frontend/signinList";
	}
	//我的积分
	@RequestMapping(value = "/myPoint",method = RequestMethod.GET)
	public String myPoint(HttpServletRequest request) {
		return "frontend/mypoint";
	}
	
	//默认登录
	@RequestMapping(value = "/imoocSignIn",method = RequestMethod.GET)
	public String imoocSignIn(HttpServletRequest request) {
		return "frontend/api-signIn";
	}
	
	//默认注册
	@RequestMapping(value = "/apiSignUp",method = RequestMethod.GET)
	public String apiSignUp(HttpServletRequest request) {
		return "frontend/api-signUp";
	}
	
	//标准绑定
	@RequestMapping(value = "/apiBanding",method = RequestMethod.GET)
	public String apiBanding(HttpServletRequest request) {
		return "frontend/api-banding";
	}

	//我的订阅页面
	@RequestMapping(value = "/mySubscribe",method = RequestMethod.GET)
	public String mySubscribe(HttpServletRequest request) {
		return "frontend/mySubscribe";
	}

	//我的历史记录页面
	@RequestMapping(value = "/myHistory",method = RequestMethod.GET)
	public String myHistory(HttpServletRequest request) {
		return "frontend/myHistory";
	}

	//我的兑换记录页面
	@RequestMapping(value = "/myExchange",method = RequestMethod.GET)
	public String myExchange(HttpServletRequest request) {
		return "frontend/myExchange";
	}
	
}
