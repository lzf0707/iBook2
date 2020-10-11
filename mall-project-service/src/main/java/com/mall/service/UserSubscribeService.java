package com.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dto.ResultExecution;
import com.mall.entity.UserSubscribeEntity;
import com.mall.model.Book;

/**
 * 
 *
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:36:25
 */
public interface UserSubscribeService extends IService<UserSubscribeEntity> {

    /*
    * 分页查询订阅记录
    * */
    ResultExecution<Book> getUserBookList(Long userId, Page<Book> page);
}

