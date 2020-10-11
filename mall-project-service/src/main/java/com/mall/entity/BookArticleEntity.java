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
@TableName("tb_book_article")
public class BookArticleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer articleId;
	/**
	 * 章节名称
	 */
	private String articleName;
	/**
	 * 章节描述
	 */
	private String articleDesc;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date lastEditTime;
	/**
	 * 可用状态
	 */
	private Integer enableStatus;
	/**
	 * 所属的书名
	 */
	private Integer bookId;
	/**
	 * 本章观看所需积分
	 */
	private Integer point;
	/**
	 * 优先级
	 */
	private Integer priority;

}
