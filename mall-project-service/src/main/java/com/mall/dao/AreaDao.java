package com.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.entity.AreaEntity;
import com.mall.model.Area;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:26:53
 */
public interface AreaDao extends BaseMapper<AreaEntity> {
	/*
	 * 新增区域信息
	 * @return
	 * */
	int addArea(Area area);

	/*
	 * 修改区域信息
	 * @return 
	 * */
	int updateArea(Area area);
	
	/*
	 * 删除区域信息
	 * @return 
	 * */
	int deleteAreaById(long areaId);
	
	/*
	 * 批量删除区域信息
	 * @return
	 * */
	int batchDeleteAreaByIds(@Param("areaIdList") List<Long> areaIdlist);
	
	/*
	 * 查询区域
	 * @return
	 * */
	List<Area> queryArea(Area area);
	
	/*
	 * 通过id查询区域
	 * @return
	 * */
	Area queryAreaById(long areaId);
}
