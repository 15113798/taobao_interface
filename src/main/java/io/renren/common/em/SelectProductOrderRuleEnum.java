package io.renren.common.em;


//榜单枚举
public enum SelectProductOrderRuleEnum {
    comprehensive("综合","1"),
    new_arrival("最新上架","2"),
    sales("销量","3"),
    coupon_size("领券量","4"),
    com_rate("佣金比例","5"),
    price("价格","6");



    //中文名称
    private String name;
    //编码
    private String code;

    private SelectProductOrderRuleEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public static SelectProductOrderRuleEnum getEnum(String code) {
        for (SelectProductOrderRuleEnum emu : SelectProductOrderRuleEnum.values()) {
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
