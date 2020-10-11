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
@TableName("tb_person_info")
public class PersonInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer userId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 用户头像
	 */
	private String profileImg;
	/**
	 * 邮箱地址
	 */
	private String email;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 账号状态
	 */
	private Integer enableStatus;
	/**
	 * 用户类型
	 */
	private Integer userType;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date lastEditTime;

}
