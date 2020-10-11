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
@TableName("tb_local_auth")
public class LocalAuthData implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExcelProperty("")
	private Integer localAuthId;
	@ExcelProperty("对应用户信息表id")
	private Integer userId;
	@ExcelProperty("用户昵称")
	private String userName;
	@ExcelProperty("密码")
	private String password;
	@ExcelProperty("创建时间")
	private Date createTime;
	@ExcelProperty("修改时间")
	private Date lastEditTime;

}
