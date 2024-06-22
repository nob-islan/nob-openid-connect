package nob.example.opappproject.constants;

/**
 * JWT向けの定数クラスです。
 * 
 * @author nob
 */
public class JwtConst {

    /**
     * トークンのjwtエンコード・デコード時のシークレットキー
     */
    public static final String SECRET_KEY = "MAzVs6JfgzdrTRk8md47yYwu";

    /**
     * 有効期間（1日）
     */
    public static final Integer VALIDITY_PERIOD_DAY = 1;

    /**
     * audience
     */
    public static final String AUDIENCE = "nob-rp";

    /**
     * issuer
     */
    public static final String ISSUER = "nob-op";
}
