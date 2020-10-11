package com.mall.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.entity.UserRecodeEntity;
import com.mall.model.UserRecode;

/**
 * 
 * 
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:26:53
 */
public interface UserRecodeDao extends BaseMapper<UserRecodeEntity> {
	/*
	 * 获取观看领积分记录
	 * @return
	 * */
	List<UserRecode> queryUserRecodeList(@Param("userRecode") UserRecode userRecode,
			@Param("pageIndex") int pageIndex,@Param("pageSize") int pageSize);
	
	/*
	 * 获取观看领积分记录总数
	 * @return
	 * */
	int queryUserRecodeCount(@Param("userRecode") UserRecode userRecode);
	
	/*
	 * 根据用户id、章节id获取观看零积分记录
	 * @return
	 * */
	UserRecode queryUserRecodeByUserAndArticleId(@Param("userId") long userId,
			@Param("articleId") long articleId);
	
	/*
	 * 新增
	 * @return
	 * */
	int insertUserRecode(UserRecode userRecode);
	
	/*
	 * 获取积分总数
	 * @return
	 * */
	int queryAllUserRecodePoint(@Param("userRecode") UserRecode userRecode);
}
