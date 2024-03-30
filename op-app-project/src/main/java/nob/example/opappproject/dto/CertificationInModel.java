package nob.example.opappproject.dto;

import lombok.Data;

/**
 * 認証API向けのinModelです。
 * 
 * @author nob
 */
@Data
public class CertificationInModel {

    /**
     * ユーザID
     */
    private String userId;

    /**
     * パスワード
     */
    private String password;
}