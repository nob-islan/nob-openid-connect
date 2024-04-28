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
     * ベースURL
     */
    public static final String BASE_URL = "/api/rp";

    /**
     * 認可エンドポイントへのリダイレクトAPI
     */
    public static final String AUTHORIZATION_REDIRECT = "/authorization/redirect";

    /**
     * アクセストークン取得API
     */
    public static final String TOKEN_FETCH = "/token/fetch";
}
