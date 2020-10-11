package com.mall.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.security.ApiUser;
import com.mall.api.security.SecurityUtils;
import com.mall.common.util.HttpServletRequestUtil;
import com.mall.common.util.PageParam;
import com.mall.dto.ResultExecution;
import com.mall.entity.UserPointEntity;
import com.mall.enums.StateEnum;
import com.mall.model.UserPoint;
import com.mall.security.dto.SimpleResponse;
import com.mall.service.UserArticleService;
import com.mall.service.UserPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/frontend/p")
@Controller
public class UserArticleController {
    @Autowired
    public UserArticleService userArticleService;
    @Autowired
    public UserPointService userPointService;
    /**
     * 兑换记录列表获取 + 剩余积分 + 消费积分
     * @return
     * */
    @RequestMapping(value = "getUserArticleList",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<SimpleResponse> getUserArticleList(HttpServletRequest request){
        ApiUser apiUser = SecurityUtils.getUser();
        String info = HttpServletRequestUtil.getString(request,"searchInfo");
        Integer bookId =  HttpServletRequestUtil.getInt(request,"bookId");
        PageParam pageParam = new PageParam();
        pageParam.setCurrent(HttpServletRequestUtil.getLong(request,"pageIndex"));
        pageParam.setSize(HttpServletRequestUtil.getLong(request,"pageSize"));
        ResultExecution rs = userArticleService.getUserArticleList(pageParam,info,(long)apiUser.getuId(),(long)bookId);
        Map<String,Object> rsMap = new HashMap<>();
        rsMap.put("count",rs.getCount());
        rsMap.put("list",rs.getList());
        UserPointEntity userPointEntity = userPointService.getOne(new LambdaQueryWrapper<UserPointEntity>().eq(UserPointEntity::getUserId,apiUser.getuId()));
        rsMap.put("userPoint",userPointEntity == null ? 0 : userPointEntity.getPoint());
        Integer exChangePoint = userArticleService.getExChangePoint(apiUser.getuId());
        rsMap.put("exChangePoint",exChangePoint);
        return ResponseEntity.ok(new SimpleResponse(rsMap, StateEnum.OK.getState()));
    }
}
