package com.mall.Queue;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;
import lombok.Data;

@Data
@Component
//用于存放订单处理对象
public class DeferredResultHolder {
	Map<String, DeferredResult<String>> map = new HashMap<String, DeferredResult<String>>();
}
