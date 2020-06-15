package io.renren.common.exception;


import io.renren.common.em.StatusEnum;

public class StatusInfo implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6356740638464532420L;

    /** 定数 */
    private StatusEnum status = null;
    
    // =======================================================================
    
    /**
     * コンストラクタ。<br />
     * @param status 
     */
    public StatusInfo(StatusEnum status) {
        if (status == null) {
            throw new NullPointerException();
        }
        this.status = status;
    }

    // =======================================================================

    /**
     * エラー内容を説明する文言を取得する。<br />
     * @return エラー内容を説明する文言
     */
    public String getErrDescription() {
        return status.getDescription();
    }

    // =======================================================================

    /**
     * ステータスクラスを取得する。<br />
     * @return ステータスクラス
     */
    public int getStatus1() {
        return status.getStatus1();
    }

    // =======================================================================

    /**
     * ステータスクラスの16進数文字列を取得する。<br />
     * @return ステータスクラスの16進数文字列
     */
    public String getStatus1HexString() {
        return status.getStatus1HexString();
    }

    // =======================================================================

    /**
     * 詳細コードを取得する。<br />
     * @return 詳細コード
     */
    public int getStatus2() {
        return status.getStatus2();
    }

    // =======================================================================

    /**
     * 詳細コードの16進数文字列を取得する。<br />
     * @return 詳細コードの16進数文字列
     */
    public String getStatus2HexString() {
        return status.getStatus2HexString();
    }
    
    // =======================================================================
    /**
     * ステータスを取得する。<br />
     * @return ステータス定数
     */
    public StatusEnum getStatusEnum() {
        return this.status;
    }

    /**
     * VOのダンプ。<br />
     * @return ダンプ文字列
     */
    public String toString() {

        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(this.status.toString());
        return sb.toString();
    }

    /**
     * StatusInfo へ変換する。<br />
     * @param status ENUM定数
     * @return 変換後のStatusInfoオブジェクト
     */
    public static StatusInfo toStatusInfo(final StatusEnum status) {
        StatusInfo si = new StatusInfo(status);
        return si;
    }

    /**
     * 比較メソッド。<br />
     * @param o 比較元
     * @return 同じ = true 違う場合 = false
     */
    public boolean equals(Object o) {
        
        if (o == null) {
            return false;
        }
        
        if (!(o instanceof StatusInfo)) {
            return false;
        }
        
        StatusInfo info = StatusInfo.class.cast(o);
        return this.status.equals(info.status);
    }
    
    /**
     * ハッシュコードの取得。<br />
     * @return Enumのハッシュ値
     */
    public int hashCode() {
        return this.status.hashCode();
    }
}
