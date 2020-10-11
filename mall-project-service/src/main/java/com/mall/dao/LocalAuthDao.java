package com.mall.dao;

import com.mall.entity.LocalAuthEntity;
import com.mall.model.LocalAuth;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * 
 * 
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:26:53
 */
public interface LocalAuthDao extends BaseMapper<LocalAuthEntity> {
	LocalAuth selectByUsername(String username);
	
	LocalAuth selectByMobile(String mobile);
	
	LocalAuth selectByLocalAuthId(String localAuthId);
	
}
