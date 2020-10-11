package com.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.entity.UserHistoryEntity;
import com.mall.model.UserHistory;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:26:53
 */
public interface UserHistoryDao extends BaseMapper<UserHistoryEntity> {
    IPage<UserHistory> selectUserHistoryList(Page<UserHistory> page,@Param("info") String info, @Param("userId") Long userId,@Param("bookId") Long bookId);
}
