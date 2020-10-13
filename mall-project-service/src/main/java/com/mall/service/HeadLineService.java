package com.mall.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dto.ImageHolder;
import com.mall.dto.ResultExecution;
import com.mall.entity.HeadLineEntity;
import com.mall.model.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;;

/**
 * 
 *
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:36:25
 */
@CacheConfig(cacheNames = "headLineService")
public interface HeadLineService extends IService<HeadLineEntity> {
	/*
	   *  增加头条
	 * @return
	 * */
	ResultExecution<HeadLine> addHeadLine(HeadLine headLine,ImageHolder thumbnail);
	
	/*
	 * 修改头条
	 * @return
	 * */
	ResultExecution<HeadLine> modifyHeadLine(HeadLine headLine,ImageHolder thumbnail);
	
	/*
	 * 删除头条
	 * @return
	 * */
	ResultExecution<HeadLine> removeHeadLine(Long lineId);
	
	/*
	 * 批量删除
	 * @return
	 * */
	ResultExecution<HeadLine> batchRemoveHeadLine(List<Long> list);
	
	/*
	 * 查询头条
	 * @return
	 * */
	@Cacheable
	List<HeadLine> getHeadLineList(HeadLine headLine);
}

