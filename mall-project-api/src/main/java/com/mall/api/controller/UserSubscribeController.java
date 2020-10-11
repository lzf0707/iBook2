package com.mall.api.controller;

import com.mall.api.security.ApiUser;
import com.mall.api.security.SecurityUtils;
import com.mall.common.util.HttpServletRequestUtil;
import com.mall.common.util.PageParam;
import com.mall.dto.ResultExecution;
import com.mall.enums.StateEnum;
import com.mall.model.Book;
import com.mall.security.dto.SimpleResponse;
import com.mall.service.UserSubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/frontend/p")
public class UserSubscribeController {
    @Autowired
    public UserSubscribeService userSubscribeService;

    @RequestMapping(value = "queryUserBookList")
    @ResponseBody
    public ResponseEntity<SimpleResponse> queryUserBookList(HttpServletRequest request){
        ApiUser apiUser = SecurityUtils.getUser();
        PageParam pageParam = new PageParam();
        pageParam.setCurrent(HttpServletRequestUtil.getLong(request,"pageIndex"));
        pageParam.setSize(HttpServletRequestUtil.getLong(request,"pageSize"));
        ResultExecution<Book> rs = userSubscribeService.getUserBookList((long)apiUser.getuId(),pageParam);
        return ResponseEntity.ok(new SimpleResponse(rs, StateEnum.OK.getState()));
    }
}
