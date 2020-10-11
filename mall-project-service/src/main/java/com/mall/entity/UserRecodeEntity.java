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
@TableName("tb_user_recode")
public class UserRecodeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户观看记录
	 */
	@TableId
	private Integer userRecodeId;
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
	 * 观看获取积分
	 */
	private Integer point;
	/**
	 * 章节所属书籍id
	 */
	private Integer bookId;

}
