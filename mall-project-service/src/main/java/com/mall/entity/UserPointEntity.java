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
@TableName("tb_user_point")
public class UserPointEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户积分id
	 */
	@TableId
	private Integer userPointId;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 用户可用积分
	 */
	private Integer point;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date lastEditTime;

}
