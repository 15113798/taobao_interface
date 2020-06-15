package io.renren.common.em;



//消息发送枚举
public enum  NoticeTypeEnum {
    Register("注册","1"),
    Reset_Password("重置密码","2");


    //中文名称
    private String name;
    //编码
    private String code;

    private NoticeTypeEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public static NoticeTypeEnum getEnum(String code) {
        for (NoticeTypeEnum emu : NoticeTypeEnum.values()) {
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
