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
@TableName("tb_head_line")
public class HeadLineEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer lineId;
	/**
	 * 导航栏名称
	 */
	private String lineName;
	/**
	 * 导航栏地址
	 */
	private String lineLink;
	/**
	 * 导航宣传地址路径
	 */
	private String lineImg;
	/**
	 * 优先级
	 */
	private Integer priority;
	/**
	 * 可用状态 1 可用 0 禁用
	 */
	private Integer enableStatus;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date lastEditTime;

}
