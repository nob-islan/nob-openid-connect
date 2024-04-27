package nob.example.rpappproject.rest.constants;

/**
 * REST通信に関する定数を管理するクラスです。
 * 
 * @author nob
 */
public class UrlConst {

    /**
     * OP Appのオリジン
     */
    public static final String OP_APP_ORIGIN = "http://localhost:8081";

    /**
     * ベースURL
     */
    public static final String BASE_URL = "/api/op";

    /**
     * 認可API
     */
    public static final String AUTHORIZATION = "/authorization";

    /**
     * トークン発行API
     */
    public static final String TOKEN = "/token";

    /**
     * UserInfo取得API
     */
    public static final String USERINFO = "/userinfo";
}
