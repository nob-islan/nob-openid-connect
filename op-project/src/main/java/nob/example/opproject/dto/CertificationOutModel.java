package nob.example.opproject.dto;

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
    private boolean isCertificated;
}
