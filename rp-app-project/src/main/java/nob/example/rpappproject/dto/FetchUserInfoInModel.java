package nob.example.rpappproject.dto;

import lombok.Data;

/**
 * ユーザ情報取得API向けのinModelです。
 * 
 * @author nob
 */
@Data
public class FetchUserInfoInModel {

    /**
     * ユーザID
     */
    private String userId;
}
