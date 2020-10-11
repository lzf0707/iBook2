package com.mall.dao;

import com.mall.entity.UserSigninEntity;
import com.mall.model.UserSignin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:26:53
 */
public interface UserSigninDao extends BaseMapper<UserSigninEntity> {
	/*
	 * 获取签到记录
	 * @return
	 * */
	IPage<UserSignin> queryUserSigninList(Page<UserSignin> page,@Param("userSignin") UserSignin userSignin);
	
	/*
	 * 获取签到记录总数
	 * @return
	 * */
	int queryUserSigninCount(@Param("userSignin") UserSignin userSignin,
			@Param("pageIndex") int pageIndex,@Param("pageSize") int pageSize);
	
	/*
	 * 判断用户今日是否签到
	 * @return
	 * */
	UserSignin queryUsersigninByCTime(long userId);
	
	/*
	 * 插入签到记录
	 * @return
	 * */
	int insertUserSignin(UserSignin userSignin);

	/*
	 * 获取签到积分总值
	 * @return
	 * */
	int queryAllUserSigninPoint(@Param("userSignin") UserSignin userSignin);
}
