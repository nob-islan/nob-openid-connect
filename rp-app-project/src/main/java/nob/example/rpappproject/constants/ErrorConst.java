package nob.example.rpappproject.constants;

/**
 * エラーに関する定数クラスです。
 * 
 * @author nob
 */
public class ErrorConst {

    /**
     * エラーコード
     * 
     */
    public class Code {

        /**
         * IDトークンが不正
         */
        public static final String INVALID_ID_TOKEN = "INVALID_ID_TOKEN";
    }

    /**
     * エラーメッセージ
     * 
     */
    public class Message {

        /**
         * 認証に失敗しました。
         */
        public static final String BASE = "認証に失敗しました。";
    }
}
