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
@TableName("tb_head_line")
public class HeadLineData implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExcelProperty("主键")
	private Integer lineId;
	@ExcelProperty("导航栏名称")
	private String lineName;
	@ExcelProperty("导航栏地址")
	private String lineLink;
	@ExcelProperty("导航宣传地址路径")
	private String lineImg;
	@ExcelProperty("优先级")
	private Integer priority;
	@ExcelProperty("可用状态 1 可用 0 禁用")
	private Integer enableStatus;
	@ExcelProperty("创建时间")
	private Date createTime;
	@ExcelProperty("修改时间")
	private Date lastEditTime;

}
