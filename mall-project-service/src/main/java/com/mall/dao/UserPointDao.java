package com.mall.dao;

import com.mall.entity.UserPointEntity;
import com.mall.model.UserPoint;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 
 * 
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:26:53
 */
public interface UserPointDao extends BaseMapper<UserPointEntity> {
	/*
	 * 用户个人积分新增
	 * */
	int insertUserPoint(UserPoint userPoint);
	
	/*
	 * 用户个人积分更新
	 * */
	int updateUserPoint(UserPoint userPoint);
	
	/*
	 * 获取用户个人积分
	 * */
	UserPoint queryUserPointByUserId(long userId);
}
