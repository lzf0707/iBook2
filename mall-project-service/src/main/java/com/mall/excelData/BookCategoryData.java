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
@TableName("tb_book_category")
public class BookCategoryData implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExcelProperty("主键")
	private Integer bookCategoryId;
	@ExcelProperty("类别名称")
	private String bookCategoryName;
	@ExcelProperty("类别描述")
	private String bookCategoryDesc;
	@ExcelProperty("类别图路径地址")
	private String bookCategoryImg;
	@ExcelProperty("优先级")
	private Integer priority;
	@ExcelProperty("创建时间")
	private Date createTime;
	@ExcelProperty("修改时间")
	private Date lastEditTime;
	@ExcelProperty("父级id")
	private Integer parentId;

}
