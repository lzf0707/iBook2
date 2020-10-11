package com.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dto.ResultExecution;
import com.mall.entity.UserRecodeEntity;
import com.mall.model.UserRecode;

/**
 * 
 *
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:36:25
 */
public interface UserRecodeService extends IService<UserRecodeEntity> {
	/*
	 * 观看记录领积分
	 * @return
	 * */
	ResultExecution<UserRecode> addUserRecode(UserRecode recode);

	/*
	 * 积分领取记录查询
	 * */
	ResultExecution<UserRecode> getUserRecodeList(UserRecode userRecode,
			int page,int pageSize);
}

