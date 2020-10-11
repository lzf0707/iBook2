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
@TableName("tb_usre_history")
public class UsreHistoryData implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExcelProperty("历史记录id")
	private Integer tbUserHistory;
	@ExcelProperty("用户id")
	private Integer userId;
	@ExcelProperty("章节id")
	private Integer articleId;
	@ExcelProperty("")
	private Date createTime;
	@ExcelProperty("章节所属id")
	private Integer bookId;

}
