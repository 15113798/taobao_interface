package io.renren.modules.common.service;


import io.renren.common.utils.UrlToMapUtil;
import io.renren.modules.common.entity.ReNTbkItem;
import io.renren.modules.common.manage.TaobaoAPIManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

@Service
public class TaoBaoService {
    private TaobaoAPIManager tAPIManager = new TaobaoAPIManager();

    public List<ReNTbkItem> getGoodsInfo(String Url){
        Url= StringEscapeUtils.unescapeHtml(Url);
        String[] path = Url.split("&")[0].split("=",0);
        // data get
        List<ReNTbkItem> result = new ArrayList<ReNTbkItem>();
        ReNTbkItem errItem = new ReNTbkItem();
        errItem.setErrCode("500");
        try {
            if(path.length <= 1){
                errItem.setErrMsg("请输入正确的链接地址!");
                result.add(errItem);
                return result;
            }
            //在不同的平台，传上来的url都不同。部分url是调用接口找不到商品的。最原始的为https://item.taobao.com/item.htm?id=575726002555
            Map<String,Objects>paramsMap = UrlToMapUtil.getUrlParams(Url);
            String ids = String.valueOf(paramsMap.get("id"));
            result = tAPIManager.getTbkItemInfoForWeb(ids);
            if( result != null && result.size() > 0 ){
                ReNTbkItem item = result.get(0);
                if( item.getErrCode() != null && item.getErrCode().equals("50") ){
                    result.clear();
                    errItem.setErrCode("501");
                    errItem.setErrMsg(item.getErrMsg());
                    result.add(errItem);
                }else{
                    // 判断淘宝或者是天猫
                    item.setProduct_type(checkProduct(path[0].toString()));
                    item.setErrCode("200");
                    result.set(0, item);
                }
            }

        } catch (Exception e) {
            errItem.setErrCode("501");
            errItem.setErrMsg("服务器繁忙，请稍等！！");
            result.add(errItem);
        }
        return result;
    }


    public String checkProduct(String link){

        if( link.indexOf("taobao")  != -1 ){
            return "1";
        }else {
            if( link.indexOf("tmall") != -1 ){
                return "2";
            }
            return "0";
        }
    }


    public void getActDetail(String id) throws IOException {
        String urlPath = "https://pub.alimama.com/cp/event/item/list.json?eventId="+id+"&category=&auditorId=&auditStatus=&keyword=&toPage=1&perPageSize=40&t=1591609990017&_tb_token_=7b18e8e873e7e&pvid=";
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

            System.out.println("活动报名商品："+tilte);
        }


    }






}
