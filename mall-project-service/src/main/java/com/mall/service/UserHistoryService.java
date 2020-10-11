package com.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dto.ResultExecution;
import com.mall.entity.UserHistoryEntity;
import com.mall.model.UserHistory;

/**
 * 
 *
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:36:25
 */
public interface UserHistoryService extends IService<UserHistoryEntity> {
    ResultExecution<UserHistory> getUserHistoryList(Page<UserHistory> page,String info,Long userId,Long bookId);
}

