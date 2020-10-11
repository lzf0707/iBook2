package com.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
import lombok.Data;
/**
 * 
 * 
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 17:49:00
 */
@Data
@TableName("tb_local_auth")
public class LocalAuthEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer localAuthId;
	/**
	 * 对应用户信息表id
	 */
	private Integer userId;
	/**
	 * 用户昵称
	 */
	@NotEmpty(message = "用户名不可为空")
	private String userName;
	/**
	 * 密码
	 */
	@NotEmpty(message = "密码不可为空")
	private String password;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date lastEditTime;
	
	private String mobile;

}
