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
@TableName("tb_book_content")
public class BookContentData implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExcelProperty("")
	private Integer bookContentId;
	@ExcelProperty("内容图片地址路径")
	private String bookContentImg;
	@ExcelProperty("描述")
	private String bookContentDesc;
	@ExcelProperty("优先级")
	private Integer priority;
	@ExcelProperty("创建时间")
	private Date createTime;
	@ExcelProperty("所属章节id")
	private Integer articleId;

}
