package io.renren.modules.common.controller;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkShopRecommendGetRequest;
import com.taobao.api.response.TbkShopRecommendGetResponse;

import io.renren.common.utils.R;
import io.renren.common.utils.UrlToMapUtil;
import io.renren.modules.common.entity.ReNTbkItem;
import io.renren.modules.common.entity.ReShopItem;
import io.renren.modules.common.service.TaoBaoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;



@RestController
@RequestMapping("/taobao")
public class TaoBaoController {

    protected static final String TBK_SHOP_FIELDS = "user_id,shop_title,shop_type,seller_nick,pict_url,shop_url";

    @Autowired
    private TaoBaoService taoBaoService;

    /**
     * 获取商品信息
     */
    @GetMapping("/getGoods")
    public R getAjaxGoods(String url) {
        List<ReNTbkItem> data = taoBaoService.getGoodsInfo(url);
        Map<String, Object> map = new HashMap<String, Object>();

        //简单校验url
        if ( data == null || data.isEmpty() || data.size() <= 0) {
            return R.error("请输入正确的商品链接地址！");
        }else if( data.size() == 1){
            ReNTbkItem item = (ReNTbkItem)data.get(0);
            map.put("errCode", item.getErrCode());
            map.put("errMsg", item.getErrMsg());
            map.put("data", item);
            return R.ok(map);
        }
        map.put("errCode", 200);
        map.put("errMsg", null);
        map.put("data", data);
        return R.ok(map);
    }



    /**
     * 获取店铺信息
     */
    @GetMapping("/getShop")
    public R getShop(String url){
        TaobaoClient client = new DefaultTaobaoClient("https://eco.taobao.com/router/rest", "25045938", "89c5d2fcc294d8bdcf0d7862373dff44");
        TbkShopRecommendGetRequest req = new TbkShopRecommendGetRequest();
        req.setFields(TBK_SHOP_FIELDS);
        Map<String, Object> map = new HashMap<String, Object>();

        Map<String, Objects>paramsMap = UrlToMapUtil.getUrlParams(url);
        Long user_number_id  = MapUtils.getLong(paramsMap, "user_number_id", 0L);
        req.setUserId(user_number_id);
        TbkShopRecommendGetResponse rsp = null;
        try {
            rsp = client.execute(req);
            List<ReShopItem> list = new ArrayList<>();
            if (rsp.isSuccess()) {
                JSONObject obj = JSONObject.fromObject(rsp.getBody());
                if(obj.has("tbk_shop_recommend_get_response")){
                    JSONObject obj1 = JSONObject.fromObject(obj.getString("tbk_shop_recommend_get_response"));
                    JSONObject obj2 = JSONObject.fromObject(obj1.getString("results"));
                    if(obj2 == null || obj2.size() == 0){
                        map.put("errCode", 500);
                        map.put("errMsg", "未查询到商铺");
                        map.put("data", null);
                        return R.ok(map);
                    }
                    JSONArray obj3 = obj2.getJSONArray("n_tbk_shop");
                    String[] fieldArr = TBK_SHOP_FIELDS.split(",",0);
                    for(int i=0; i< obj3.size();i++){
                        JSONObject obj4 = obj3.getJSONObject(i);
                        //num_iid,title,,small_images,reserve_price,,,,
                        //item_url,,,,,,,
                        ReShopItem item = new ReShopItem();
                        for(String field:fieldArr){
                            switch( field ){
                                case "user_id":
                                    item.setUser_id(obj4.getString(field));
                                    break;
                                case "shop_title":
                                    item.setShop_title(obj4.getString(field));
                                    break;
                                case "shop_type":
                                    item.setShop_type(obj4.getString(field));
                                    break;
                                case "seller_nick":
                                    item.setSeller_nick(obj4.getString(field));
                                    break;
                                case "pict_url":
                                    item.setPict_url(obj4.getString(field));
                                    break;
                                case "shop_url":
                                    item.setShop_url(obj4.getString(field));
                                    break;
                            }
                        }
                        list.add(item);
                    }
                    map.put("errCode", 200);
                    map.put("errMsg", null);
                    map.put("data", list);
                    return R.ok(map);
                }else{
                    System.out.println(rsp.getBody());
                    JSONObject obj1 = JSONObject.fromObject(obj.getString("error_response"));
                    ReShopItem item = new ReShopItem();
                    item.setErrCode("500");
                    if( obj1.has("msg") ){
                        item.setErrMsg(obj1.getString("msg"));
                        map.put("errMsg", obj1.getString("msg"));
                    }else if( obj1.has("sub_msg") ){
                        item.setErrMsg(obj1.getString("sub_msg"));
                        map.put("errMsg", obj1.getString("sub_msg"));
                    }else{
                        item.setErrMsg("未知异常，请联系管理员");
                        map.put("errMsg", null);
                    }
                    map.put("errCode", 500);
                    map.put("data", item);
                    return R.ok(map);
                }
            }
        } catch (ApiException e) {
            map.put("errCode", 500);
            map.put("errMsg", "API调用异常");
            map.put("data", null);
            return R.ok(map);
        }
        return  R.error("未知异常，请联系管理员");
    }





    /*
        爬取服务费订单明细
     */

    @GetMapping("/getOrderDetail")
    public R getOrderDetail() throws IOException {
        String urlPath = "https://pub.alimama.com/report/getCPPaymentDetails.json?t=1592233213081&_tb_token_=3b3b7de86305b&startTime=2020-06-15&endTime=2020-06-15&payStatus=&queryType=1&toPage=1&perPageSize=40&jumpType=0";
        String cookie = "t=4e3ec18c6a73c704160583d476385691; cna=7QQnF5wlCnYCAdzK4dJayAgj; account-path-guide-s1=true; 133709857-payment-time=true; cookie2=13a00fdac9c1160821d4a6b0878597ea; v=0; _tb_token_=3b3b7de86305b; JSESSIONID=889DA26DF040CDC658FB0E66151D1A79; alimamapwag=TW96aWxsYS81LjAgKFdpbmRvd3MgTlQgMTAuMDsgV2luNjQ7IHg2NCkgQXBwbGVXZWJLaXQvNTM3LjM2IChLSFRNTCwgbGlrZSBHZWNrbykgQ2hyb21lLzc1LjAuMzc3MC44MCBTYWZhcmkvNTM3LjM2; cookie32=8e95c26abf561303829e0c022a769b8d; alimamapw=H1xeUApCVAwHWAwGPgZWUgVVBwIHAwACAlpXUgRRVAVUBQIBVwIBVVYGUQYH; cookie31=MTA5MzI3MTI3LHlpbmNodWFvYmluZywxMjQ4ODg1NDNAcXEuY29tLFRC; login=UtASsssmOIJ0bQ%3D%3D; rurl=aHR0cHM6Ly9wdWIuYWxpbWFtYS5jb20v; l=eB_kP5LgQ69kquYUBOfwourza77OSIRAguPzaNbMiOCPOe1M50jRWZxuAwLHC3GVh6WHR3rFMTWQBeYBqIcTUeXD5FNxGjMmn; isg=BDAwaSoFGtRsfMaQ2-xuOWIQAf6CeRTDJbPtvSqB_Ate5dCP0onkU4bTPe2F9cyb";
        URL url = new URL(urlPath);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("Cookie", cookie);
        conn.setDoInput(true);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        String repStr = "["+sb+"]";
        JSONArray jSONArray = JSONArray.fromObject(repStr);
        JSONObject data =  ((JSONObject)jSONArray.get(0)).getJSONObject("data");
        if(null == data ){
            System.out.println("cookie失效了，请重新获取cookie");
        }
        JSONArray paymentList = data.getJSONArray("paymentList");
        for (int i =0;i<paymentList.size();i++){
            JSONObject paymentObject= paymentList.getJSONObject(i);
            String tilte = paymentObject.getString("auctionTitle");
            System.out.println("订单名称："+tilte);
        }


        return null;
    }





    /*
        爬取活动
     */

    @GetMapping("/getActInfo")
    public R getActDetail() throws IOException {
        String urlPath = "https://pub.alimama.com/cp/event/list.json?toPage=1&perPageSize=40&sceneId=6&t=1591609289511&_tb_token_=7b18e8e873e7e&pvid=";
        String cookie = "t=4e3ec18c6a73c704160583d476385691; cna=7QQnF5wlCnYCAdzK4dJayAgj; account-path-guide-s1=true; 133709857-payment-time=true; cookie2=1040010afb9c47aac48262079ad0470c; v=0; _tb_token_=7b18e8e873e7e; alimamapwag=TW96aWxsYS81LjAgKFdpbmRvd3MgTlQgMTAuMDsgV2luNjQ7IHg2NCkgQXBwbGVXZWJLaXQvNTM3LjM2IChLSFRNTCwgbGlrZSBHZWNrbykgQ2hyb21lLzc1LjAuMzc3MC44MCBTYWZhcmkvNTM3LjM2; cookie32=73b54f2902379700926005ff2249e7e1; alimamapw=ElcFB1YHAFFcCGtRAlJXAFBUUQVSBlUOBwADAQFSAAVQVABUV1MEAgZSVw%3D%3D; cookie31=MTMzNzA5ODU3LHRiNTQ0MDUyOTksNTQzMDk5NTYyQHFxLmNvbSxUQg%3D%3D; login=VT5L2FSpMGV7TQ%3D%3D; l=eB_kP5LgQ69kqq6jBOfwlurza77ORIRAguPzaNbMiOCP_h1w5v5CWZvepNLeCnGVh6WWR3rFMTWQBeYBqIcTUeXD5FNxGbDmn; isg=BENDsXKyGXQDSdV5ZM1NEGWh0gftuNf6AqKeVHUgv6IZNGNW_YsvS-WirsR6lC_y";
        URL url = new URL(urlPath);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("Cookie", cookie);
        conn.setDoInput(true);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        String repStr = "["+sb+"]";
        JSONArray jSONArray = JSONArray.fromObject(repStr);
        JSONObject data =  ((JSONObject)jSONArray.get(0)).getJSONObject("data");
        if(null == data ){
            System.out.println("cookie失效了，请重新获取cookie");
        }
        JSONArray actList = data.getJSONArray("result");
        for (int i =0;i<actList.size();i++){
            JSONObject actObject= actList.getJSONObject(i);
            String tilte = actObject.getString("title");

            String actId = actObject.getString("eventId");
            taoBaoService.getActDetail(actId);

            System.out.println("活动名称："+tilte);
        }


        return null;
    }









}
