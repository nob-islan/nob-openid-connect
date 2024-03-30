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
     * OP Appのオリジン
     */
    public static final String OP_APP_ORIGIN = "http://localhost:8081";

    /**
     * ベースURL
     */
    public static final String BASE_URL = "/api/rp";

    /**
     * 認可エンドポイントへのリダイレクトAPI
     */
    public static final String AUTHORIZATION_REDIRECT = "/authorization/redirect";

    /**
     * トークンリクエストAPI
     */
    public static final String TOKEN_DEMAND = "/token/demand";

    /**
     * ユーザ情報取得API
     */
    public static final String USERINFO_FETCH = "/userinfo/fetch";
}
