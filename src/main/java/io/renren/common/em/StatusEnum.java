package io.renren.common.em;

public enum StatusEnum {
    /** 正常開始 */
    NORMAL_START("0x00000000", "0x00000000", "正常開始", 0),
    /** 正常終了 */
    NORMAL_EXIT("0x00000000", "0x00000001", "正常終了", 0),
    
    API_TAOBAO_FAIL("0x00010001", "0x00010001", "淘宝API调用失败", 1),
    API_TAOBAO_ERR("0x00010001", "0x00010002", "淘宝API调用异常", 1),
    
    APP_REQUESTLIMIT_ERR("0x00020001", "0x00010001", "RequestLimit 异常", 1),
    APP_NOT_HTTPREQUEST_PARAM_ERR("0x00020001", "0x00010002", "not HttpRequest param 异常", 1),
    APP_NOT_ERROES_PARAM_ERR("0x00020001", "0x00010003", "not Errors param 异常", 1),
    APP_SENDSMS_TYPE_UNDEFINED_ERR("0x00020001", "0x00010003", "短信发信类型未定义异常", 1),
    
    
    SYSTEM_ERR("0x00090009", "0x00010001", "system 异常", 1),
    
    /** 異常終了（SIWWEBException以外） */
    ERROR_EXIT("0x99999999", "0x99999999", "システムエラーが発生しました。", 1)
    ;

    // ========================================================================
    
    /** 基数. */
    private static final int RADIX = 16;
    
    /** 処理ステタース１. */
    private final String status1;

    /** 処理ステタース２. */
    private final String status2;

    /** 説明. */
    private final String description;

    /** ステータス判断フラグ. */
    private final int statusFlag;

    // ========================================================================

    /**
     * コンストラクタ<br />
     * @param st1 処理ステタース１
     * @param st2 処理ステタース２
     * @param desc 説明
     */
    private StatusEnum(final String st1, final String st2, final String desc, final int flg) {
        this.status1 = st1;
        this.status2 = st2;
        this.description = desc;
        this.statusFlag = flg;
    }

    /**
     * 説明を返す<br />
     * @return 説明
     */
    public String getDescription() {
        return description;
    }

    /**
     * ステタース１を返す<br />
     * @return ステタース１
     */
    public int getStatus1() {
        return Integer.parseInt(status1.substring(2), RADIX);
    }


    /**
     * ステタース１の16進数文字列を返す<br />
     * @return ステタース１の16進数文字列
     */
    public String getStatus1HexString() {
        return status1;
    }
    
    /**
     * ステタース２を返す<br />
     * @return ステタース２
     */
    public int getStatus2() {
        return Integer.parseInt(status2.substring(2), RADIX);
    }

    /**
     * ステタース２の16進数文字列を返す<br />
     * @return ステタース２の16進数文字列
     */
    public String getStatus2HexString() {
        return status2;
    }
    
    /**
     * ステタース判断フラグを返す<br />
     * @return ステタース判断フラグ
     */
    public int getStatusFlag() {
        return statusFlag;
    }

    /**
     * Enumのデータを文字列として返す<br />
     * @return 文字列化されたデータ
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Status1:[" + this.status1 + "] ");
        sb.append("Status2:[" + this.status2 + "] ");
        sb.append("Description:[" + this.description + "]");
        sb.append("StatusFlag:[" + this.statusFlag + "]");
        return sb.toString();
    }
}
