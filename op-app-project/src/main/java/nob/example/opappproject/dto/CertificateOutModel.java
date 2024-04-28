package nob.example.opappproject.dto;

import lombok.Data;

/**
 * 認証API向けのoutModelです。
 * 
 */
@Data
public class CertificateOutModel {

    /**
     * ユーザID
     */
    private String userId;

    /**
     * 認可コード
     */
    private String authorizationCode;

    /**
     * リダイレクトURI
     */
    private String redirectUri;
}