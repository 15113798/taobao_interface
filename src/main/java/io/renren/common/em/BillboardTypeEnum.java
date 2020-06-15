package io.renren.common.em;


//榜单枚举
public enum BillboardTypeEnum {
    multipleList("综合榜单","1"),
    RealTimeList("实时销量榜","2"),
    AllDayList("全天销量榜","3"),
    HotPushList("热推榜","4"),
    HotBuyList("买家热搜榜","5"),
    DuplicateList("复购榜","6");



    //中文名称
    private String name;
    //编码
    private String code;

    private BillboardTypeEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public static BillboardTypeEnum getEnum(String code) {
        for (BillboardTypeEnum emu : BillboardTypeEnum.values()) {
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
