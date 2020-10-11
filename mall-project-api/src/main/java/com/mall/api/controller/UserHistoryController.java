package com.mall.api.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.security.ApiUser;
import com.mall.api.security.SecurityUtils;
import com.mall.common.util.HttpServletRequestUtil;
import com.mall.common.util.PageParam;
import com.mall.dto.ResultExecution;
import com.mall.entity.UserHistoryEntity;
import com.mall.enums.StateEnum;
import com.mall.security.dto.SimpleResponse;
import com.mall.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping(value = "/frontend/p")
public class UserHistoryController {
    @Autowired
    public UserHistoryService userHistoryService;

    /**
    * 观看记录添加
    * @return
    */
    @RequestMapping(value = "addHistoryRecode",method = RequestMethod.POST)
    public ResponseEntity<SimpleResponse> addHistoryRecode(HttpServletRequest request, Authentication auth){
        Integer bookId = HttpServletRequestUtil.getInt(request,"bookId");
        Integer articleId = HttpServletRequestUtil.getInt(request,"articleId");
        ApiUser apiUser = (ApiUser)auth.getPrincipal();
        if(bookId > 0 && articleId > 0){
            //查看是否有书籍历史记录
            Wrapper wrapper = new LambdaQueryWrapper<UserHistoryEntity>()
                    .eq(UserHistoryEntity::getUserId,apiUser.getuId())
                    .eq(UserHistoryEntity::getBookId,bookId);
            UserHistoryEntity userHistoryEntity = userHistoryService.getOne(wrapper);
            Boolean rs = false;
            //若存在，执行更新，否则执行插入
            if(userHistoryEntity != null){
                userHistoryEntity.setArticleId(articleId);
                userHistoryEntity.setCreateTime(new Date());
                rs = userHistoryService.update(userHistoryEntity,wrapper);
            } else {
                userHistoryEntity = new UserHistoryEntity();
                userHistoryEntity.setArticleId(articleId);
                userHistoryEntity.setCreateTime(new Date());
                userHistoryEntity.setBookId(bookId);
                userHistoryEntity.setUserId(apiUser.getuId());
                rs = userHistoryService.save(userHistoryEntity);
            }
            if(rs){
                return ResponseEntity.ok(new SimpleResponse("新增历史记录成功!", StateEnum.OK.getState()));
            }else{
                return ResponseEntity.ok(new SimpleResponse("新增历史记录失败!", StateEnum.FAILD.getState()));
            }
        }
        return ResponseEntity.ok(new SimpleResponse("bookId为空或articleId为空", StateEnum.OK.getState()));
    }

    /**
     * 获取历史记录
     * @return
     * */
    @RequestMapping(value = "getUserHistoryList",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<SimpleResponse> getUserHistoryList(HttpServletRequest request){
        ApiUser apiUser = SecurityUtils.getUser();
        String info = HttpServletRequestUtil.getString(request,"searchInfo");
        Integer bookId =  HttpServletRequestUtil.getInt(request,"bookId");
        PageParam pageParam = new PageParam();
        pageParam.setCurrent(HttpServletRequestUtil.getLong(request,"pageIndex"));
        pageParam.setSize(HttpServletRequestUtil.getLong(request,"pageSize"));
        ResultExecution rs = userHistoryService.getUserHistoryList(pageParam,info,(long)apiUser.getuId(),(long)bookId);
        return ResponseEntity.ok(new SimpleResponse(rs,StateEnum.OK.getState()));
    }
}
