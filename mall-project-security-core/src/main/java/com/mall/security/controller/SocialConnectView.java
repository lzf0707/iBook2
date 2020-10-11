package com.mall.security.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public class SocialConnectView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		if(model.get("connections") == null) {  //解绑
			response.getWriter().write("<h3>解绑成功！</h3>");
		}else {
			response.getWriter().write("<h3>绑定成功！</h3>");
		}
		
	}

}
