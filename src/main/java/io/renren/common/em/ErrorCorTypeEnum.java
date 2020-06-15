package io.renren.common.em;



//纠错枚举
public enum ErrorCorTypeEnum {
    PriceNo("价格不对/优惠券异常","1"),
    CommissionNo("佣金不对/计划取消","2"),
    CommodiInf("商品劣质/虚假/不发货","3"),
    OtherAno("其他异常","4"),
    ListCor("榜单纠错","5");


    //中文名称
    private String name;
    //编码
    private String code;

    private ErrorCorTypeEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public static ErrorCorTypeEnum getEnum(String code) {
        for (ErrorCorTypeEnum emu : ErrorCorTypeEnum.values()) {
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
