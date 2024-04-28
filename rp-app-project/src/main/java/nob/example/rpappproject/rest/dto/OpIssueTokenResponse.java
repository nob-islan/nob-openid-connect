package nob.example.rpappproject.rest.dto;

import lombok.Data;

/**
 * トークン発行API向けのレスポンスです。
 * 
 * @author nob
 */
@Data
public class OpIssueTokenResponse {

    /**
     * アクセストークン
     */
    private String accessToken;

    /**
     * リフレッシュトークン
     */
    private String refleshToken;

    /**
     * IDトークン
     */
    private String idToken;
}
