package nob.example.opproject.dto;

import lombok.Data;

/**
 * 認証API向けのinModelです。
 * 
 * @author nob
 */
@Data
public class CertificationInModel {

    // TODO バリデーション, swagger

    /**
     * ユーザID
     */
    private String userId;

    /**
     * パスワード
     */
    private String password;
}
