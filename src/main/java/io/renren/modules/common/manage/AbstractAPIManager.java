package io.renren.modules.common.manage;

public abstract class AbstractAPIManager {

    protected static final String HTTP_SERVICE_URL = "http://gw.api.taobao.com/router/rest";
    protected static final String HTTPS_SERVICE_URL = "https://eco.taobao.com/router/rest";
    protected static final String STG_HTTP_SERVICE_URL = "http://gw.api.tbsandbox.com/router/rest";
    protected static final String STG_HTTPS_SERVICE_URL = "https://gw.api.tbsandbox.com/router/rest";
    
    // 1）获取授权码（code）
    // 正式环境
    protected static final String HTTPS_AUTH_SERVICE_URL = "https://oauth.taobao.com/authorize";
    // 沙箱环境：
    protected static final String STG_HTTPS_AUTH_SERVICE_URL = "https://oauth.tbsandbox.com/authorize";
    // 2）获取访问令牌（access_token）
    // 正式环境
    protected static final String HTTPS_TOKEN_SERVICE_URL = "https://oauth.taobao.com/token";
    // 沙箱环境
    protected static final String STG_HTTPS_TOKEN_SERVICE_URL = "https://oauth.tbsandbox.com/token";

    protected static final String APPKEY = "25045938";
    protected static final String APPSECRET = "89c5d2fcc294d8bdcf0d7862373dff44";

    protected static final String TBK_ITEMINFO_FIELDS = "num_iid,title,pict_url,small_images,reserve_price,tk_rate,commission_rate,zk_final_price,user_type,provcity,item_url,nick,seller_id,volume,cat_leaf_name,cat_name";

    // Access Key ID
    protected static final String ACCESSKEYID = "LTAIRtqK2nkXab9v";

    // Access Key Secret
    protected static final String ACCESSKEYSECRET = "sf0JD7J6QErnBTUxlbAfPHpbAXL5lf";

    // 修改密码验证码
    protected static final String SMS_66630017 = "SMS_66630017";

    // 用户注册验证码
    protected static final String SMS_66630019 = "SMS_66630019";

    // 信息变更验证码
    protected static final String SMS_66630016 = "SMS_66630016";

    // 登录确认验证码
    protected static final String SMS_66630021 = "SMS_66630021";

    // 登录异常验证码
    protected static final String SMS_66630020 = "SMS_66630020";

    // 渠道赚 114882275
    protected static final String SIGNNAME_QUDAO = "渠道赚";

    // 注册验证
    protected static final String SIGNNAME_REGISTER = "注册验证";

    // 注册验证
    protected static final String SIGNNAME_LOGIN = "登录验证";

    // 变更验证
    protected static final String SIGNNAME_UPDATE = "变更验证";

    // 身份验证
    protected static final String SIGNNAME_HONNIN = "身份验证";

    protected static final String DEFAULTCONNECTTIMEOUT = "5000";
    protected static final String DEFAULTREADTIMEOUT = "10000";
    protected static final String TEMPLATEPARAM_CODE = "#CODE#";
    protected static final String TEMPLATEPARAM_NAME = "#NAME#";
    protected static final String TEMPLATEPARAM = "{\"product\":\"[#NAME#]\", \"code\":\"#CODE#\"}";

}
