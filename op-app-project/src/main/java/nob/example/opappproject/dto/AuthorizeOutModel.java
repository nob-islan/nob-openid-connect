package nob.example.opappproject.dto;

import lombok.Data;

/**
 * 認可API向けのoutModelです。
 * 
 */
@Data
public class AuthorizeOutModel {

    /**
     * リダイレクトURI
     */
    private String redirectUri;
}
