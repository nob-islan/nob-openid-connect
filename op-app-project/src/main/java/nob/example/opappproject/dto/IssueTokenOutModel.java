package nob.example.opappproject.dto;

import lombok.Data;

/**
 * トークン発行API向けのoutModelです。
 * 
 * @author nob
 */
@Data
public class IssueTokenOutModel {

    /**
     * アクセストークン
     */
    private String accessToken;

    /**
     * IDトークン
     */
    private String idToken;
}
