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
@TableName("tb_person_info")
public class PersonInfoData implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExcelProperty("")
	private Integer userId;
	@ExcelProperty("名称")
	private String name;
	@ExcelProperty("用户头像")
	private String profileImg;
	@ExcelProperty("邮箱地址")
	private String email;
	@ExcelProperty("性别")
	private String gender;
	@ExcelProperty("账号状态")
	private Integer enableStatus;
	@ExcelProperty("用户类型")
	private Integer userType;
	@ExcelProperty("创建时间")
	private Date createTime;
	@ExcelProperty("修改时间")
	private Date lastEditTime;

}
