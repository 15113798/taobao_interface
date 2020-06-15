package io.renren.common.em;


//反馈问题枚举
public enum FeedBackEnum {
    Commodity("商品库相关问题","1"),
    OpenPlatform("开放平台相关问题","2"),
    CMSAPP("CMS/App相关问题","3"),
    ShortVideo("短视频带货相关问题","4"),
    ReleaseList("招商放单相关问题","5"),
    CashRealer("变现客相关问题","6"),
    OtherQuestions("其他问题","7");


    //中文名称
    private String name;
    //编码
    private String code;

    private FeedBackEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public static FeedBackEnum getEnum(String code) {
        for (FeedBackEnum emu : FeedBackEnum.values()) {
            if (emu.code.equals(code)) {
                return emu;
            }
        }
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



}
