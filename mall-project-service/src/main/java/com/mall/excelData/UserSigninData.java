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
@TableName("tb_user_signin")
public class UserSigninData implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExcelProperty("签到id")
	private Integer userSigninId;
	@ExcelProperty("用户id")
	private Integer userId;
	@ExcelProperty("")
	private Date createTime;
	@ExcelProperty("签到获取积分")
	private Integer point;

}
