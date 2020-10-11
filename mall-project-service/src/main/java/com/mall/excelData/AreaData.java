package com.mall.excelData;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:23:34
 */
@Data
@TableName("tb_area")
public class AreaData implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExcelProperty("主键")
	private Integer areaId;
	@ExcelProperty("区域名称")
	private String areaName;
	@ExcelProperty("优先级")
	private Integer priority;
	@ExcelProperty("创建时间")
	private Date createTime;
	@ExcelProperty("修改时间")
	private Date lastEditTime;

}
