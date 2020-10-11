package com.mall.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dto.ResultExecution;
import com.mall.entity.AreaEntity;
import com.mall.model.Area;

/**
 * 
 *
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:36:25
 */
public interface AreaService extends IService<AreaEntity> {
	/*
	 * 获取区域列表
	 * @return 
	 * */
	List<Area> getAreaList();
	
	/*
	 * 增加区域信息
	 * @return
	 * */
	ResultExecution<Area> addArea(Area area);
	
	/*
	 * 修改区域信息
	 * @return
	 * */
	ResultExecution<Area> modifyArea(Area area);
	
	/*
	 * 删除区域信息
	 * @return
	 * */
	ResultExecution<Area> removeArea(Area area);
	
	/*
	 * 根据ID获取区域信息
	 * @return 
	 * */
	Area getAreaById(Long areaId);
}

