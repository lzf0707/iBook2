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
@TableName("tb_book")
public class BookData implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExcelProperty("主键")
	private Integer bookId;
	@ExcelProperty("作者")
	private Integer owerId;
	@ExcelProperty("所属区域")
	private Integer areaId;
	@ExcelProperty("所属类别")
	private Integer bookCategoryId;
	@ExcelProperty("书名")
	private String bookName;
	@ExcelProperty("描述")
	private String bookDesc;
	@ExcelProperty("封面图地址路径")
	private String bookImg;
	@ExcelProperty("优先级")
	private Integer priority;
	@ExcelProperty("创建时间")
	private Date createTime;
	@ExcelProperty("修改时间")
	private Date lastEditTime;
	@ExcelProperty("状态 0 禁用 1 可用")
	private Integer enableStatus;
	@ExcelProperty("完结状态 0 连载 1完结")
	private Integer endStatus;
	@ExcelProperty("作者")
	private String author;

}
