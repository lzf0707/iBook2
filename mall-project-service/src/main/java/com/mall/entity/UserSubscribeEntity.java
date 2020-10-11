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
@TableName("tb_user_subscribe")
public class UserSubscribeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订阅id
	 */
	@TableId
	private Integer userSubscribeId;
	/**
	 * 用户 id
	 */
	private Integer userId;
	/**
	 * 书籍id
	 */
	private Integer bookId;
	/**
	 * 
	 */
	private Date createTime;

}
