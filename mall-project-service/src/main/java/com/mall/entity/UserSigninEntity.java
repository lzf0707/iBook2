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
@TableName("tb_user_signin")
public class UserSigninEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 签到id
	 */
	@TableId
	private Integer userSigninId;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 签到获取积分
	 */
	private Integer point;

}
