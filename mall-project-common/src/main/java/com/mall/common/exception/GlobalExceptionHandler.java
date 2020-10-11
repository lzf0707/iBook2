package com.mall.common.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

//统一处理controller层抛出的异常
//异常对用户不可见，统一输出在日志上
@ControllerAdvice
public class GlobalExceptionHandler {

	public GlobalExceptionHandler() {
		// TODO Auto-generated constructor stub
	}
	
	private final static Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Map<String, Object> handle(Exception e){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("success", false);
		LOG.error("系统异常:" + e.getMessage());
		
		modelMap.put("errMsg", "未知错误，请联系工作人员进行解决");	
		return modelMap;
	}
	
	
	@ExceptionHandler(value = OperationException.class)
	@ResponseBody
	public Map<String, Object> oeprationHandle(OperationException e){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("success", false);
		LOG.error("自定义异常:" + e.getMessage());
		modelMap.put("errMsg", e.getMessage());	
		return modelMap;
	}
	
	@ExceptionHandler(value = IOException.class)
	@ResponseBody
	public Map<String, Object> IOHandle(IOException e){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("success", false);
		LOG.error("IOException:" + e.getMessage());
		modelMap.put("errMsg", "未知错误，请联系工作人员进行解决");	
		return modelMap;
	}
	
	
	@ExceptionHandler(value = JsonMappingException.class)
	@ResponseBody
	public Map<String, Object> JsonMappingHandle(JsonMappingException e){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("success", false);
		LOG.error("JsonMappingException:" + e.getMessage());
		modelMap.put("errMsg", "未知错误，请联系工作人员进行解决");	
		return modelMap;
	}
	
	
	@ExceptionHandler(value = JsonProcessingException.class)
	@ResponseBody
	public Map<String, Object> JsonProcessingHandle(JsonProcessingException e){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("success", false);
		LOG.error("JsonProcessingException:" + e.getMessage());
		modelMap.put("errMsg", "未知错误，请联系工作人员进行解决");	
		return modelMap;
	}
}
