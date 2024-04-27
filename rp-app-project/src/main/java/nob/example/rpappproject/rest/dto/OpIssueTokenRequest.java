package nob.example.rpappproject.rest.dto;

import lombok.Data;

/**
 * トークン発行API向けのリクエストです。
 * 
 * @author nob
 */
@Data
public class OpIssueTokenRequest {

    /**
     * 認可コード
     */
    private String authorizationCode;

    /**
     * 検証用のランダム文字列
     */
    private String codeVerifier;
}
