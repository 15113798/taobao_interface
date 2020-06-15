package io.renren.modules.common.manage;

import com.taobao.api.ApiRuleException;
import com.taobao.api.BaseTaobaoRequest;
import com.taobao.api.internal.util.RequestCheckUtils;
import com.taobao.api.internal.util.TaobaoHashMap;
import com.taobao.api.response.TbkItemInfoGetResponse;

import java.util.Map;

public class TbkItemInfoGetRequest extends BaseTaobaoRequest<TbkItemInfoGetResponse> {
	private String fields;
	private Long platform;
	private String numIids;

	public Long getPlatform() {
		return platform;
	}

	public void setPlatform(Long platform) {
		this.platform = platform;
	}

	public String getNumIids() {
		return numIids;
	}

	public void setNumIids(String numIids) {
		this.numIids = numIids;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

    public Map<String, String> getTextParams() {
	  TaobaoHashMap txtParams = new TaobaoHashMap();
	  txtParams.put("fields", this.fields);
	  txtParams.put("platform", this.platform);
	  txtParams.put("num_iids", this.numIids);

	  if(this.udfParams != null) {
		  txtParams.putAll(this.udfParams);
	  }
	  return txtParams;
	}
    public String getApiMethodName() {
	      return "taobao.tbk.item.info.get";
	}

    public Class<TbkItemInfoGetResponse> getResponseClass() {
      return TbkItemInfoGetResponse.class;
    }

    public void check() throws ApiRuleException {
       RequestCheckUtils.checkNotEmpty(this.fields, "fields");
    }
}
