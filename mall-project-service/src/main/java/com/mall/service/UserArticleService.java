package com.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dto.ResultExecution;
import com.mall.entity.UserArticleEntity;
import com.mall.model.UserArticle;
import org.springframework.cache.annotation.Cacheable;

/**
 * 
 *
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:36:25
 */
public interface UserArticleService extends IService<UserArticleEntity> {
    ResultExecution<UserArticleEntity> unLockArticle(String flag,Integer bookId,Integer articleId,Integer userId);

    int getUnLockArticleTotalPoint(Integer bookId,Integer userId);

    int getExChangePoint(Integer userId);

    ResultExecution<UserArticle> getUserArticleList(Page<UserArticle> page, String info, Long userId, Long bookId);
}

