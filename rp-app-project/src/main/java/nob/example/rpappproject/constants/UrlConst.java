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
    public static final String AUTHORIZATION_REDIRECT = "/redirect/authorization";

    /**
     * ユーザ情報取得API
     */
    public static final String USER_INFO = "/userinfo";
}
