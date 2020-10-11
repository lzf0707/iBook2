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
@TableName("tb_user_point")
public class UserPointData implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExcelProperty("用户积分id")
	private Integer userPointId;
	@ExcelProperty("用户id")
	private Integer userId;
	@ExcelProperty("用户可用积分")
	private Integer point;
	@ExcelProperty("")
	private Date createTime;
	@ExcelProperty("")
	private Date lastEditTime;

}
