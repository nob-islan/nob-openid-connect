package nob.example.rpappproject.rest.constants;

/**
 * REST通信に関する定数を管理するクラスです。
 * 
 * @author nob
 */
public class UrlConst {

    /**
     * RP Webのオリジン
     */
    public static final String RP_WEB_ORIGIN = "http://localhost:3000";

    /**
     * RP ログイン後トップ画面
     */
    public static final String TOP = "/top";

    /**
     * OP Appのオリジン
     */
    public static final String OP_APP_ORIGIN = "http://localhost:8081";

    /**
     * OP ベースURL
     */
    public static final String BASE_URL = "/api/op";

    /**
     * OP 認可API
     */
    public static final String AUTHORIZATION = "/authorization";

    /**
     * OP トークン発行API
     */
    public static final String TOKEN = "/token";
}
