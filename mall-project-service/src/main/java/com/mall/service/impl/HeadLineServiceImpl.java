package com.mall.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.HeadLineDao;
import com.mall.dto.ImageHolder;
import com.mall.dto.ResultExecution;
import com.mall.entity.HeadLineEntity;
import com.mall.model.HeadLine;
import com.mall.service.HeadLineService;

import ma.glasnost.orika.MapperFacade;


@Service
public class HeadLineServiceImpl extends ServiceImpl<HeadLineDao, HeadLineEntity> implements HeadLineService {

	@Autowired
	private HeadLineDao headLineDao;
	@Autowired
	private MapperFacade mapperFacade;
	
	@Override
	public ResultExecution<HeadLine> addHeadLine(HeadLine headLine, ImageHolder thumbnail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultExecution<HeadLine> modifyHeadLine(HeadLine headLine, ImageHolder thumbnail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultExecution<HeadLine> removeHeadLine(Long lineId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultExecution<HeadLine> batchRemoveHeadLine(List<Long> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HeadLine> getHeadLineList(HeadLine headLine) {
		// TODO Auto-generated method stub
		//返回格式转换
		return mapperFacade.mapAsList(
				headLineDao.selectList(new LambdaQueryWrapper<HeadLineEntity>()
						.eq(HeadLineEntity::getEnableStatus,1)),
				HeadLine.class);
	}

   

}