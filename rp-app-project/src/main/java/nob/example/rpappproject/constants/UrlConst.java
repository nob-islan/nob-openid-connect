package nob.example.rpappproject.constants;

/**
 * URLを管理する定数クラスです。
 * 
 * @author nob
 */
public class UrlConst {

    /**
     * RP Webのオリジン
     */
    public static final String RP_WEB_ORIGIN = "http://localhost:3000";

    /**
     * RP ベースURL
     */
    public static final String BASE_URL = "/api/rp";

    /**
     * アクセストークン取得API
     */
    public static final String TOKEN_FETCH = "/token/fetch";

    /**
     * OP Webのオリジン
     */
    public static final String OP_WEB_ORIGIN = "http://localhost:3001";

    /**
     * 認可APIリダイレクト画面
     */
    public static final String REDIRECT_AUTHORIZE = "/redirect/authorize";
}
