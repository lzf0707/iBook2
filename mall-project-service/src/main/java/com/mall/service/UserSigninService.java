package com.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dto.ResultExecution;
import com.mall.entity.UserSigninEntity;
import com.mall.model.UserSignin;

/**
 * 
 *
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:36:25
 */
public interface UserSigninService extends IService<UserSigninEntity> {
	/*
	 * 签到
	 * @return
	 * */
	ResultExecution<UserSignin> addUserSignin(UserSignin userSignin);
	
	
	/*
	 * 签到记录查询
	 * @return
	 * */
	ResultExecution<UserSignin> getUserSigninList(UserSignin userSignin,Page<UserSignin> page);
	
	/*
	 * 获取今日签到记录
	 * @return
	 * */
	UserSignin getUsersigninByCTime(long userId);
}

