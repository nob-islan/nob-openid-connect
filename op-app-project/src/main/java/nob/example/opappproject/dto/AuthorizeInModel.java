package nob.example.opappproject.dto;

import lombok.Data;

/**
 * 認可API向けのinModelです。
 * 
 * @author nob
 */
@Data
public class AuthorizeInModel {

    /**
     * クライアントID
     */
    private String clientId;

    /**
     * リダイレクトURI
     */
    private String redirectUri;
}
