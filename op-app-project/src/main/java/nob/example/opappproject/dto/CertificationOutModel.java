package nob.example.opappproject.dto;

import lombok.Data;

/**
 * 認証API向けのoutModelです。
 * 
 */
@Data
public class CertificationOutModel {

    /**
     * 認証の成否
     */
    private Boolean isCertificated;
}