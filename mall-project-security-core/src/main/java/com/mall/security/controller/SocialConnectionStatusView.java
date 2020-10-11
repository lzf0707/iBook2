package com.mall.security.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("connect/status")
public class SocialConnectionStatusView extends AbstractView{

	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		Map<String,List<Connection<?>>> connections = (Map<String, List<Connection<?>>>) model.get("connectionMap");
		//循环结果，输出到前端
		Map<String,Boolean> result = new HashMap<String, Boolean>();
		for (String key : connections.keySet()) {
			result.put(key, CollectionUtils.isNotEmpty(connections.get(key)));
		}
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(result));
	}

}
