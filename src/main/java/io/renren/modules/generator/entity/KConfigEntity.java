package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * Config 系统配置
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-08 16:20:33
 */
@Data
@TableName("k_config")
public class KConfigEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 配置ID
	 */
	@TableId
	private Integer id;
	/**
	 * 配置名称
	 */
	private String name;
	/**
	 * 配置说明
	 */
	private String title;
	/**
	 * 配置分组
	 */
	private String group;
	/**
	 * 配置说明
	 */
	private String remark;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 配置值
	 */
	private String value;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
