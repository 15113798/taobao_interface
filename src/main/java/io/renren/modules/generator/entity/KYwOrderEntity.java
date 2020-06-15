package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 淘宝服务费订单明细表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-15 23:50:37
 */
@Data
@TableName("k_yw_order")
public class KYwOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 服务费率
	 */
	private String tkservicerate;
	/**
	 * 商品单价
	 */
	private BigDecimal payprice;
	/**
	 * 商品链接
	 */
	private String auctionurl;
	/**
	 * 商品id
	 */
	private String auctionid;
	/**
	 * 
	 */
	private String tksharerate;
	/**
	 * 预估付款服务费
	 */
	private BigDecimal preservicefee;
	/**
	 * 
	 */
	private String presell;
	/**
	 * 付款金额
	 */
	private BigDecimal totalalipayfeestring;
	/**
	 * 订单创建时间
	 */
	private Date createtime;
	/**
	 * 订单状态
	 */
	private String paystatus;
	/**
	 * 商品名称
	 */
	private String auctiontitle;
	/**
	 * 所属店铺
	 */
	private String exshoptitle;
	/**
	 * 结算金额
	 */
	private String realpayfeestring;
	/**
	 * 
	 */
	private String relationid;
	/**
	 * 
	 */
	private String specialid;
	/**
	 * 
	 */
	private String inviteraccountname;
	/**
	 * 
	 */
	private String sharerate;
	/**
	 * 
	 */
	private String relationapp;
	/**
	 * 
	 */
	private String tkalimmsharerate;
	/**
	 * 
	 */
	private String inviterid;
	/**
	 * 
	 */
	private Date earningtime;
	/**
	 * 
	 */
	private String tkbiztag;
	/**
	 * 
	 */
	private String tk3rdpubsharefee;
	/**
	 * 
	 */
	private String tk3rdtypestr;
	/**
	 * 
	 */
	private String exmemberid;
	/**
	 * 
	 */
	private String realpayfee;
	/**
	 * 
	 */
	private String terminaltype;
	/**
	 * 
	 */
	private String auctionnum;
	/**
	 * 
	 */
	private String taobaotradeparentid;
	/**
	 * 
	 */
	private String exnickname;
	/**
	 * 
	 */
	private String tkshareratetostring;
	/**
	 * 
	 */
	private String tkpubsharefeestring;
	/**
	 * 
	 */
	private String feestring;
	/**
	 * 
	 */
	private String discountandsubsidytostring;
	/**
	 * 
	 */
	private String finaldiscounttostring;
	/**
	 * 
	 */
	private String prefinishservicefee;
	/**
	 * 
	 */
	private String biztype;
	/**
	 * 插表时间
	 */
	private Date insertTime;

}
