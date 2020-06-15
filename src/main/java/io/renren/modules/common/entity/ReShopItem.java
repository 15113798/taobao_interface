package io.renren.modules.common.entity;


import com.taobao.api.TaobaoObject;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class ReShopItem extends TaobaoObject {
	private static final long serialVersionUID = 4355335399576832685L;
	@ApiField("user_id")
	private String user_id;
	@ApiField("shop_title")
	private String shop_title;
	@ApiField("shop_type")
	private String shop_type;
	@ApiField("seller_nick")
	private String seller_nick;
	@ApiField("pict_url")
	private String pict_url;
	@ApiField("shop_url")
	private String shop_url;
	@ApiField("errMsg")
	private String errMsg;
	@ApiField("errCode")
	private String errCode;

}
