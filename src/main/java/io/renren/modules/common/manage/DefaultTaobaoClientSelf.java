package io.renren.modules.common.manage;

import com.taobao.api.*;
import com.taobao.api.internal.cluster.ClusterManager;
import com.taobao.api.internal.cluster.DnsConfig;
import com.taobao.api.internal.parser.json.ObjectJsonParser;
import com.taobao.api.internal.parser.xml.ObjectXmlParser;
import com.taobao.api.internal.util.*;

import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.util.Date;
import java.util.Map;

public class DefaultTaobaoClientSelf implements TaobaoClient {
   protected String serverUrl;
   protected String appKey;
   protected String appSecret;
   protected String format;
   protected String signMethod;
   protected int connectTimeout;
   protected int readTimeout;
   protected boolean needCheckRequest;
   protected boolean needEnableParser;
   protected boolean useSimplifyJson;
   protected boolean useGzipEncoding;
   private boolean isHttpDnsEnabled;
   private String originalHttpHost;

   public DefaultTaobaoClientSelf(String serverUrl, String appKey, String appSecret) {
      this.format = "json";
      this.signMethod = "hmac";
      this.connectTimeout = 15000;
      this.readTimeout = 30000;
      this.needCheckRequest = true;
      this.needEnableParser = true;
      this.useSimplifyJson = false;
      this.useGzipEncoding = true;
      this.isHttpDnsEnabled = false;
      this.originalHttpHost = null;


      this.appKey = appKey;
      this.appSecret = appSecret;
      this.serverUrl = serverUrl;
   }

   public DefaultTaobaoClientSelf(String serverUrl, String appKey, String appSecret, String format) {
      this(serverUrl, appKey, appSecret);
      this.format = format;
   }

   public DefaultTaobaoClientSelf(String serverUrl, String appKey, String appSecret, String format, int connectTimeout, int readTimeout) {
      this(serverUrl, appKey, appSecret, format);
      this.connectTimeout = connectTimeout;
      this.readTimeout = readTimeout;
   }

   public DefaultTaobaoClientSelf(String serverUrl, String appKey, String appSecret, String format, int connectTimeout, int readTimeout, String signMethod) {
      this(serverUrl, appKey, appSecret, format, connectTimeout, readTimeout);
      this.signMethod = signMethod;
   }

   public <T extends TaobaoResponse> T execute(TaobaoRequest<T> request) throws ApiException {
      return this.execute(request, (String)null);
   }

   public <T extends TaobaoResponse> T execute(TaobaoRequest<T> request, String session) throws ApiException {
      return this._execute(request, session);
   }

   private <T extends TaobaoResponse> T _execute(TaobaoRequest<T> request, String session) throws ApiException {
      long start = System.currentTimeMillis();

      Object parser = null;
      if(this.needEnableParser) {
         if("xml".equals(this.format)) {
            parser = new ObjectXmlParser(request.getResponseClass());
         } else {
            parser = new ObjectJsonParser(request.getResponseClass(), this.useSimplifyJson);

         }
      }

      if(this.needCheckRequest) {
         try {
           request.check();
         } catch (ApiRuleException arg19) {
            TaobaoResponse appParams = null;
            try {
               appParams = (TaobaoResponse)request.getResponseClass().newInstance();
            } catch (Exception arg17) {
              throw new ApiException(arg17);
            }
            appParams.setErrorCode(arg19.getErrCode());
            appParams.setMsg(arg19.getErrMsg());
           return (T)appParams;
         }
      }

      RequestParametersHolder requestHolder = new RequestParametersHolder();
      TaobaoHashMap appParams1 = new TaobaoHashMap(request.getTextParams());
      requestHolder.setApplicationParams(appParams1);


      TaobaoHashMap protocalMustParams = new TaobaoHashMap();
      protocalMustParams.put("method", request.getApiMethodName());
      protocalMustParams.put("v", "2.0");
      protocalMustParams.put("app_key", this.appKey);
      Long timestamp = request.getTimestamp();
      if(timestamp == null) {
         timestamp = Long.valueOf(System.currentTimeMillis());
      }

      protocalMustParams.put("timestamp", new Date(timestamp.longValue()));
      requestHolder.setProtocalMustParams(protocalMustParams);

     TaobaoHashMap protocalOptParams = new TaobaoHashMap();
      protocalOptParams.put("format", this.format);
      protocalOptParams.put("sign_method", this.signMethod);
      protocalOptParams.put("session", session);
      protocalOptParams.put("partner_id", this.getSdkVersion());
      protocalOptParams.put("target_app_key", request.getTargetAppKey());

      if(this.useSimplifyJson) {
         protocalOptParams.put("simplify", Boolean.TRUE.toString());
      }
      requestHolder.setProtocalOptParams(protocalOptParams);

      String tRsp;
      try {
         protocalMustParams.put("sign", TaobaoUtils.signTopRequest(requestHolder, this.appSecret, this.signMethod));

         tRsp = this.getServerUrl(this.serverUrl, request.getApiMethodName(), session, appParams1);
         String e = WebUtils.buildQuery(requestHolder.getProtocalMustParams(), "UTF-8");
         String sysOptQuery = WebUtils.buildQuery(requestHolder.getProtocalOptParams(), "UTF-8");
         String fullUrl = WebUtils.buildRequestUrl(tRsp, new String[]{e, sysOptQuery});

         String rsp = null;

         if(this.useGzipEncoding) {
            request.getHeaderMap().put("Accept-Encoding", "gzip");
         }
         if(this.getTopHttpDnsHost() != null) {
            request.getHeaderMap().put("TOP_HTTP_DNS_HOST", this.getTopHttpDnsHost());
         }

        if(request instanceof TaobaoUploadRequest) {
            TaobaoUploadRequest uRequest = (TaobaoUploadRequest)request;
            Map fileParams = TaobaoUtils.cleanupMap(uRequest.getFileParams());
            rsp = WebUtils.doPost(fullUrl, appParams1, fileParams, "UTF-8", this.connectTimeout, this.readTimeout, request.getHeaderMap());
         } else {
            rsp = WebUtils.doPost(fullUrl, appParams1, "UTF-8", this.connectTimeout, this.readTimeout, request.getHeaderMap(), (Proxy)null);
         }
         requestHolder.setResponseBody(rsp);
      } catch (IOException arg20) {
         TaobaoLogger.logApiError(this.appKey, request.getApiMethodName(), this.serverUrl, requestHolder.getAllParams(), System.currentTimeMillis() - start, arg20.toString());
         throw new ApiException(arg20);
      }

      tRsp = null;
      TaobaoResponse tRsp1;
      if(!this.needEnableParser) {


         //这api有问题，parse的参数是传2个字符，  ken-20181113
         //TODO ken 20181113
         tRsp1 = (TaobaoResponse)((TaobaoParser)parser).parse(requestHolder.getResponseBody(),"");
         tRsp1.setBody(requestHolder.getResponseBody());
      } else {
         try {
            tRsp1 = (TaobaoResponse)request.getResponseClass().newInstance();
            tRsp1.setBody(requestHolder.getResponseBody());
         } catch (Exception arg18) {
            throw new ApiException(arg18);
         }
      }

      tRsp1.setParams(appParams1);
      if(!tRsp1.isSuccess()) {
         TaobaoLogger.logApiError(this.appKey, request.getApiMethodName(), this.serverUrl, requestHolder.getAllParams(), System.currentTimeMillis() - start, tRsp1.getBody());
      }
      return (T)tRsp1;
   }

   public String getServerUrl(String serverUrl, String apiName, String session, TaobaoHashMap appParams) {
      if(this.isHttpDnsEnabled) {
         DnsConfig dnsConfig = ClusterManager.GetDnsConfigFromCache();
         return dnsConfig == null?serverUrl:dnsConfig.getVipUrl(serverUrl);




      } else {
         return serverUrl;
      }
   }
   protected String getSdkVersion() {
      return this.isHttpDnsEnabled?"top-sdk-java-httpdns-20180323":"top-sdk-java-20180323";
   }



   public void setNeedCheckRequest(boolean needCheckRequest) {
      this.needCheckRequest = needCheckRequest;
   }




   public void setNeedEnableParser(boolean needEnableParser) {
      this.needEnableParser = needEnableParser;
   }




   public void setUseSimplifyJson(boolean useSimplifyJson) {
      this.useSimplifyJson = useSimplifyJson;
   }




   public void setNeedEnableLogger(boolean needEnableLogger) {
      TaobaoLogger.setNeedEnableLogger(needEnableLogger);
   }




   public void setIgnoreSSLCheck(boolean ignore) {
      WebUtils.setIgnoreSSLCheck(ignore);
   }




   public void setUseGzipEncoding(boolean useGzipEncoding) {
      this.useGzipEncoding = useGzipEncoding;
   }




   public void setConnectTimeout(int connectTimeout) {
      this.connectTimeout = connectTimeout;
   }




   public void setReadTimeout(int readTimeout) {
      this.readTimeout = readTimeout;
   }

   public String getTopHttpDnsHost() {
      return this.isHttpDnsEnabled?this.originalHttpHost:null;
   }

   public void enableHttpDns() {
      WebUtils.setIgnoreHostCheck(true);
      this.setHttpDnsHost(this.serverUrl);
      ClusterManager.initRefreshThread(this.appKey, this.appSecret);
      this.isHttpDnsEnabled = true;
   }







   public void enableHttpDns(String onlineAppKey, String onlineAppSecret) {
      WebUtils.setIgnoreHostCheck(true);
      this.setHttpDnsHost(this.serverUrl);
      ClusterManager.initRefreshThread(onlineAppKey, onlineAppSecret);
      this.isHttpDnsEnabled = true;
   }

   public void resetServerUrl(String serverUrl) {
      this.serverUrl = serverUrl;
   }

   public String getRequestUrl() {
      return this.serverUrl;
   }
   public String getAppKey() {
          return this.appKey;
   }

   private void setHttpDnsHost(String serverUrl) {
      if(serverUrl != null && !serverUrl.isEmpty()) {
          try {
              URL e = new URL(serverUrl);
              this.originalHttpHost = e.getHost();
          } catch (Exception arg2) {
              throw new RuntimeException("error serverUrl:" + serverUrl, arg2);
              }

          }
      }
   }

