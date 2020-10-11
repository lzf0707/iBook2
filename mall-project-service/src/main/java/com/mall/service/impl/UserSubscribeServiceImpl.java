package com.mall.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.exception.OperationException;
import com.mall.dto.ResultExecution;
import com.mall.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.UserSubscribeDao;
import com.mall.entity.UserSubscribeEntity;
import com.mall.service.UserSubscribeService;


@Service
public class UserSubscribeServiceImpl extends ServiceImpl<UserSubscribeDao, UserSubscribeEntity> implements UserSubscribeService {

    @Autowired
    private UserSubscribeDao userSubscribeDao;

    @Override
    public ResultExecution<Book> getUserBookList(Long userId, Page<Book> page) {
        ResultExecution<Book> rs = new ResultExecution<>();
        try {
            IPage<Book> bookIPage = userSubscribeDao.selectBookList(page,userId);
            rs.setList(bookIPage.getRecords());
            rs.setCount((int) bookIPage.getTotal());
        }catch (Exception e){
            throw new OperationException("获取用户订阅列表失败-" + e.getMessage());
        }
        return rs;
    }
}