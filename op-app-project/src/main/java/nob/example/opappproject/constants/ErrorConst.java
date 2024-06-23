package nob.example.opappproject.constants;

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
         * リダイレクトURIが不正
         */
        public static final String INVALID_REDIRECT_URI = "INVALID_REDIRECT_URI";

        /**
         * クレデンシャル情報が不正
         */
        public static final String INVALID_CREDENTIAL = "INVALID_CREDENTIAL";

        /**
         * 認可コードが不正
         */
        public static final String INVALID_AUTHORIZATION_CODE = "INVALID_AUTHORIZATION_CODE";

        /**
         * codeVerifierが不正
         */
        public static final String INVALID_CODE_VERIFIER = "INVALID_CODE_VERIFIER";
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
