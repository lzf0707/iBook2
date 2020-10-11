package com.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 17:49:00
 */
@Data
@TableName("tb_book_category")
public class BookCategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer bookCategoryId;
	/**
	 * 类别名称
	 */
	private String bookCategoryName;
	/**
	 * 类别描述
	 */
	private String bookCategoryDesc;
	/**
	 * 类别图路径地址
	 */
	private String bookCategoryImg;
	/**
	 * 优先级
	 */
	private Integer priority;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date lastEditTime;
	/**
	 * 父级id
	 */
	private Integer parentId;

}
