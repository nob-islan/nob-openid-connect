package nob.example.rpappproject.dto;

import lombok.Data;

/**
 * アクセストークンリクエスト向けのoutModelです。
 * 
 * @author nob
 */
@Data
public class DemandTokenOutModel {

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
