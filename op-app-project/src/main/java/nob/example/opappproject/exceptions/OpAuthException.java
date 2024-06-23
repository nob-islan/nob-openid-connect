package nob.example.opappproject.exceptions;

import lombok.Getter;

/**
 * 認証エラーの例外クラスです。
 * 
 * @author nob
 */
public class OpAuthException extends Exception {

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
    public OpAuthException(String code, String message) {

        this.code = code;
        this.message = message;
    }
}
