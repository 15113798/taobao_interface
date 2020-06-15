package io.renren.modules.common.manage;


import com.taobao.api.ApiException;

import com.taobao.api.response.TbkItemInfoGetResponse;


import io.renren.common.em.StatusEnum;
import io.renren.common.exception.ApplicationException;
import io.renren.common.utils.log.AppLogger;
import io.renren.common.utils.log.AppLoggerFactory;
import io.renren.modules.common.entity.ReNTbkItem;
import net.sf.json.JSONArray;

import net.sf.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import static io.renren.modules.common.manage.AbstractAPIManager.*;

public class TaobaoAPIManager {
    private static final AppLogger log = AppLoggerFactory.getLogger(TaobaoAPIManager.class);
    private DefaultTaobaoClientSelf client;

    public TaobaoAPIManager() {
        client = new DefaultTaobaoClientSelf(HTTPS_SERVICE_URL, APPKEY, APPSECRET);
    }

    public List<ReNTbkItem> getTbkItemInfoForWeb(String itemIds) throws Exception {
        return getTbkItemInfoForWeb(TBK_ITEMINFO_FIELDS, 1L, itemIds);
    }

    public List<ReNTbkItem> getTbkItemInfoForWeb(String fields, Long platform, String itemIds) throws Exception {
        List<ReNTbkItem> result = new ArrayList<ReNTbkItem>();
        TbkItemInfoGetRequest req = new TbkItemInfoGetRequest();
        req.setFields(fields);
        req.setPlatform(platform);
        req.setNumIids(itemIds);
        try {
            TbkItemInfoGetResponse rsp = client.execute(req);
            if (rsp.isSuccess()) {
                JSONObject obj = JSONObject.fromObject(rsp.getBody());
                if(obj.has("tbk_item_info_get_response")){
                    JSONObject obj1 = JSONObject.fromObject(obj.getString("tbk_item_info_get_response"));
                    JSONObject obj2 = JSONObject.fromObject(obj1.getString("results"));
                    JSONArray obj3 = obj2.getJSONArray("n_tbk_item");
                    String[] fieldArr = TBK_ITEMINFO_FIELDS.split(",",0);
                    for(int i=0; i< obj3.size();i++){
                        JSONObject obj4 = obj3.getJSONObject(i);
                        //num_iid,title,,small_images,reserve_price,,,,
                        //item_url,,,,,,,
                        ReNTbkItem item = new ReNTbkItem();
                        for(String field:fieldArr){
                            switch( field ){
                            case "num_iid":
                                item.setNumIid(obj4.getLong(field));
                                break;
                            case "title":
                                item.setTitle(obj4.getString(field));
                                break;
                            case "pict_url":
                                item.setPictUrl(obj4.getString(field));
                                break;
                            case "small_images":
                                //item.setSmall_images(obj4.getJSONArray(field));
                                break;
                            case "reserve_price":
                                item.setReserve_price(obj4.getString(field));
                                break;
                            case "zk_final_price":
                                item.setZk_final_price(obj4.getString(field));
                                break;
                            case "user_type":
                                item.setUser_type(obj4.getLong(field));
                                break;
                            case "provcity":
                                item.setProvcity(obj4.getString(field));
                                break;
                            case "item_url":
                                item.setItem_url(obj4.getString(field));
                                break;
                            case "nick":
                                item.setNick(obj4.getString(field));
                                break;
                            case "seller_id":
                                item.setSellerId(obj4.getLong(field));
                                break;
                            case "volume":
                                item.setVolume(obj4.getLong(field));
                                break;
                            case "cat_leaf_name":
                                item.setCat_leaf_name(obj4.getString(field));
                                break;
                            case "cat_name":
                                item.setCat_name(obj4.getString(field));
                                break;
                            case "tk_rate":
                                item.setTk_rate(obj4.containsKey(field)?obj4.getString(field):null );
                                break;
                            case "commission_rate":
                                item.setCommission_rate(obj4.containsKey(field)?obj4.getString(field):null);
                                break;
                            }
                        }
                        result.add(item);
                    }
                }else{
                    System.out.println(rsp.getBody());
                     JSONObject obj1 = JSONObject.fromObject(obj.getString("error_response"));
                     ReNTbkItem item = new ReNTbkItem();
                      item.setErrCode("50");
                      if( obj1.has("msg") ){
                          item.setErrMsg(obj1.getString("msg"));
                      }else if( obj1.has("sub_msg") ){
                          item.setErrMsg(obj1.getString("sub_msg"));
                      }else{
                         item.setErrMsg("");
                      }
                      result.add(item);
                }

                System.out.println(rsp.getBody());
            } else {
                if (log.isWarnEnabled()) {
                    log.warn(rsp.getBody());
                }
                throw new ApplicationException(StatusEnum.API_TAOBAO_FAIL);
            }
        } catch (ApiException e) {
            if (log.isWarnEnabled()) {
                log.warn("ApiException", e);
            }
            throw new ApplicationException(StatusEnum.API_TAOBAO_ERR);
        } catch (Exception e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return result;
    }

}
