package io.renren.common.exception;


import io.renren.common.em.StatusEnum;

public class ApplicationException extends Exception{

	private static final long serialVersionUID = -2392365315802396723L;
	

    // ステタースオブジェクト
    private StatusInfo statusInfo = null;
    // 付加オブジェクト配列 
    private Object[] addObj = null;
    // エラーメッセージ
    private String message = null;
    /**
     * コンストラクタ。<br />
     */
    public ApplicationException() {
        super();
    }

    /**
     * コンストラクタ。<br />
     * @param e 例外
     */
    public ApplicationException(Throwable e) {
        super(e);
    }
    
    /**
     * コンストラクタ。<br />
     * @param status          ステータスオブジェクト
     * @param argument        パラメータ
     */
    public ApplicationException(StatusInfo status, Object... argument) {
        super();
        this.statusInfo = status;
        this.addObj = toClone(argument);
    }
    
    /**
     * コンストラクタ。<br />
     * @param status          ステータスenum値
     * @param argument        パラメータ
     */
    public ApplicationException(StatusEnum status, Object... argument) {
        this(StatusInfo.toStatusInfo(status), argument);
    }

    /**
     * コンストラクタ。<br />
     * @param value          error message
     */
    public ApplicationException(String value) {
    		message = value;
    }
    
    /**
     * コンストラクタ。<br />
     * @param status          ステータスenum値
     * @param argument        パラメータ
     */
    public ApplicationException(String value, Throwable e) {
        super(e);
        message = value;
    }

    /**
     * コンストラクタ。<br />
     * @param e               例外
     * @param status          ステータスオブジェクト
     * @param argument        パラメータ
     */
    public ApplicationException(Throwable e, StatusInfo status, Object... argument) {
        this(e);
        this.statusInfo = status;
        this.addObj = toClone(argument);
    }
    
    /**
     * コンストラクタ。<br />
     * @param e               例外
     * @param status          ステータスenum値
     * @param argument        パラメータ
     */
    public ApplicationException(Throwable e, StatusEnum status, Object... argument) {
        this(e, StatusInfo.toStatusInfo(status), argument);
    }
    
    /**
     * ステタースオブジェクトを取得する。<br />
     * @return ステタースオブジェクト
     */
    public StatusInfo getStatusInfo() {
        return this.statusInfo;
    }

    /**
     * 付加オブジェクト配列を取得する。<br />
     * @return 付加オブジェクト配列
     */
    public Object[] getAddObject() {
        return toClone(this.addObj);
    }
    
    /**
     * コピーする。<br />
     * @param src ソース
     * @return コピーしたもの
     */
    private Object[] toClone(final Object[] src) {
        if (src == null) {
            return new Object[0];
        }
        Object[] dest = new Object[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }

    /**
     * エラー取得処理<BR>
     * @return String 
     */
    public String getErrorMessage(){
    	return message;
    }

}
