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
@TableName("tb_user_recode")
public class UserRecodeData implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExcelProperty("用户观看记录")
	private Integer userRecodeId;
	@ExcelProperty("用户id")
	private Integer userId;
	@ExcelProperty("章节id")
	private Integer articleId;
	@ExcelProperty("")
	private Date createTime;
	@ExcelProperty("观看获取积分")
	private Integer point;
	@ExcelProperty("章节所属书籍id")
	private Integer bookId;

}
