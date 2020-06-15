package io.renren.modules.common.entity;


import com.taobao.api.TaobaoObject;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class ReNTbkItem extends TaobaoObject {
	private static final long serialVersionUID = 4355335399576832685L;
	@ApiField("click_url")
	private String click_url;
	@ApiField("item_url")
	private String item_url;
	@ApiField("nick")
	private String nick;
	@ApiField("num_iid")
	private Long numIid;
	@ApiField("pict_url")
	private String pictUrl;
	@ApiField("provcity")
	private String provcity;
	@ApiField("reserve_price")
	private String reserve_price;
	@ApiField("seller_id")
	private Long sellerId;
	@ApiField("shop_title")
	private String shopTitle;
	@ApiListField("small_images")
	@ApiField("string")
	private List<String> small_images;
	@ApiField("title")
	private String title;
	@ApiField("tk_rate")
	private String tk_rate;
	@ApiField("user_type")
	private Long user_type;
	@ApiField("volume")
	private Long volume;
	@ApiField("zk_final_price")
	private String zk_final_price;
	@ApiField("zk_final_price_wap")
	private String zk_final_price_wap;
	@ApiField("cat_leaf_name")
	private String cat_leaf_name;
	@ApiField("cat_name")
	private String cat_name;
	@ApiField("commission_rate")
	private String commission_rate;
	@ApiField("product_type")
	private String product_type;
	@ApiField("errMsg")
	private String errMsg;
	@ApiField("errCode")
	private String errCode;
}
