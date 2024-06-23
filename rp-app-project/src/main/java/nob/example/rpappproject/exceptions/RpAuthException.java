package nob.example.rpappproject.exceptions;

import lombok.Getter;

/**
 * 認証エラーの例外クラスです。
 * 
 * @author nob
 */
public class RpAuthException extends Exception {

    /**
     * エラーコード
     */
    @Getter
    private String code;

    /**
     * エラーメッセージ
     */
    @Getter
    private String message;

    // コンストラクタ
    public RpAuthException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
