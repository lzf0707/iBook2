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
@TableName("tb_book_content")
public class BookContentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer bookContentId;
	/**
	 * 内容图片地址路径
	 */
	private String bookContentImg;
	/**
	 * 描述
	 */
	private String bookContentDesc;
	/**
	 * 优先级
	 */
	private Integer priority;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 所属章节id
	 */
	private Integer articleId;

}
