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
@TableName("tb_user_subscribe")
public class UserSubscribeData implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExcelProperty("订阅id")
	private Integer userSubscribeId;
	@ExcelProperty("用户 id")
	private Integer userId;
	@ExcelProperty("书籍id")
	private Integer bookId;
	@ExcelProperty("")
	private Date createTime;

}
