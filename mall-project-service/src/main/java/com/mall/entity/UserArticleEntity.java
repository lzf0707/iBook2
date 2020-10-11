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
@TableName("tb_user_article")
public class UserArticleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 开通章节id
	 */
	@TableId
	private Integer userArticleId;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 章节id
	 */
	private Integer articleId;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 消费积分
	 */
	private Integer point;
	/**
	 * 章节所属书籍
	 */
	private Integer bookId;

}
