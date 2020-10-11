package com.mall.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.exception.OperationException;
import com.mall.dto.ResultExecution;
import com.mall.model.UserHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.UserHistoryDao;
import com.mall.entity.UserHistoryEntity;
import com.mall.service.UserHistoryService;


@Service
public class UserHistoryServiceImpl extends ServiceImpl<UserHistoryDao, UserHistoryEntity> implements UserHistoryService {
    @Autowired
    private UserHistoryDao userHistoryDao;

    @Override
    public ResultExecution<UserHistory> getUserHistoryList(Page<UserHistory> page, String info, Long userId,Long bookId) {
        ResultExecution<UserHistory> rs = new ResultExecution<>();
        try{
            IPage<UserHistory> userHistoryIPage = userHistoryDao.selectUserHistoryList(page,info,userId,bookId);
            rs.setList(userHistoryIPage.getRecords());
            rs.setCount((int) userHistoryIPage.getTotal());
        }catch (Exception e){
            throw new OperationException("获取用户历史记录失败-" + e.getMessage());
        }
        return rs;
    }
}