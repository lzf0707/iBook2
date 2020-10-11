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
@TableName("tb_book_article")
public class BookArticleData implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExcelProperty("")
	private Integer articleId;
	@ExcelProperty("章节名称")
	private String articleName;
	@ExcelProperty("章节描述")
	private String articleDesc;
	@ExcelProperty("创建时间")
	private Date createTime;
	@ExcelProperty("修改时间")
	private Date lastEditTime;
	@ExcelProperty("可用状态")
	private Integer enableStatus;
	@ExcelProperty("所属的书名")
	private Integer bookId;
	@ExcelProperty("本章观看所需积分")
	private Integer point;
	@ExcelProperty("优先级")
	private Integer priority;

}
