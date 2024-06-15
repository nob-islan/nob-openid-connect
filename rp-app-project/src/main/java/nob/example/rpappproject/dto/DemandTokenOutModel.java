package nob.example.rpappproject.dto;

import lombok.Data;

/**
 * アクセストークン要求向けのoutModelです。
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
     * IDトークン
     */
    private String idToken;
}
