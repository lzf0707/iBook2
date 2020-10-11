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
@TableName("tb_book")
public class BookEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer bookId;
	/**
	 * 作者
	 */
	private Integer owerId;
	/**
	 * 所属区域
	 */
	private Integer areaId;
	/**
	 * 所属类别
	 */
	private Integer bookCategoryId;
	/**
	 * 书名
	 */
	private String bookName;
	/**
	 * 描述
	 */
	private String bookDesc;
	/**
	 * 封面图地址路径
	 */
	private String bookImg;
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
	 * 状态 0 禁用 1 可用
	 */
	private Integer enableStatus;
	/**
	 * 完结状态 0 连载 1完结
	 */
	private Integer endStatus;
	/**
	 * 作者
	 */
	private String author;

}
