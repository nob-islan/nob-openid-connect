package nob.example.opappproject.dto;

import lombok.Data;

/**
 * 認証API向けのoutModelです。
 * 
 */
@Data
public class CertificationOutModel {

    /**
     * 認可コード
     */
    private String authorizationCode;
}