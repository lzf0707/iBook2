package com.mall.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.exception.OperationException;
import com.mall.dao.AreaDao;
import com.mall.dto.ResultExecution;
import com.mall.entity.AreaEntity;
import com.mall.enums.StateEnum;
import com.mall.model.Area;
import com.mall.service.AreaService;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class AreaServiceImpl extends ServiceImpl<AreaDao, AreaEntity> implements AreaService {

	@Autowired
	private AreaDao areaDao;
	
	public AreaServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Area> getAreaList() {
		// TODO Auto-generated method stub
		List<Area> areaList = null;
		areaList = areaDao.queryArea(new Area());
		return areaList;
	}

	@Override
	public ResultExecution<Area> modifyArea(Area area) {
		// TODO Auto-generated method stub
		if(area.getAreaId() != null && area.getAreaId() > 0) {
			try {
				int effectedNum = areaDao.updateArea(area);
				if(effectedNum > 0) {
					return new ResultExecution<Area>(StateEnum.SUCCESS);
				}else {
					return new ResultExecution<Area>(StateEnum.INNER_ERROR);
				}
			} catch (Exception e) {
				// TODO: handle exception
				log.error(e.getMessage());
				throw new OperationException("modify area error");
			}
			
		}else {
			return new ResultExecution<Area>(StateEnum.EMPTY);
		}
	}

	@Override
	public ResultExecution<Area> removeArea(Area area) {
		// TODO Auto-generated method stub
		if(area.getAreaId() != null && area.getAreaId() > 0) {
			try {
				int effectedNum = areaDao.deleteAreaById(area.getAreaId());
				if(effectedNum > 0) {
					return new ResultExecution<Area>(StateEnum.SUCCESS);
				}else {
					return new ResultExecution<Area>(StateEnum.INNER_ERROR);
				}
			} catch (Exception e) {
				// TODO: handle exception
				log.error(e.getMessage());
				throw new OperationException("remove area error:请删除书籍关联的区域！");
			}
			
		}else {
			return new ResultExecution<Area>(StateEnum.EMPTY);
		}
	}

	@Override
	public ResultExecution<Area> addArea(Area area) {
		// TODO Auto-generated method stub
		if(area.getAreaName() != null && !area.getAreaName().equals("")) {
			try {
				int effectedNum = areaDao.addArea(area);
				if(effectedNum > 0) {
					return new ResultExecution<Area>(StateEnum.SUCCESS);
				}else {
					return new ResultExecution<Area>(StateEnum.INNER_ERROR);
				}
			} catch (Exception e) {
				// TODO: handle exception
				log.error(e.getMessage());
				throw new OperationException("add area error");
			}
			
		}else {
			return new ResultExecution<Area>(StateEnum.EMPTY);
		}
	}

	@Override
	public Area getAreaById(Long areaId) {
		// TODO Auto-generated method stub
		return areaDao.queryAreaById(areaId);
	}
}