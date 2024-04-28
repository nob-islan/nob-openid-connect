package nob.example.opappproject.constants;

/**
 * URLを管理する定数クラスです。
 * 
 * @author nob
 */
public class UrlConst {

    /**
     * OP Webのオリジン
     */
    public static final String OP_WEB_ORIGIN = "http://localhost:3001";

    /**
     * ログイン画面
     */
    public static final String LOGIN = "/login";

    /**
     * ベースURL
     */
    public static final String BASE_URL = "/api/op";

    /**
     * 認可API
     */
    public static final String AUTHORIZATION = "/authorization";

    /**
     * 認証API
     */
    public static final String CERTIFICATION = "/certification";

    /**
     * トークン発行API
     */
    public static final String TOKEN = "/token";

    /**
     * UserInfo取得API
     */
    public static final String USERINFO = "/userinfo";
}
