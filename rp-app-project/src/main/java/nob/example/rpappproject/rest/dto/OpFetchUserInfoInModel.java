package nob.example.rpappproject.rest.dto;

import lombok.Data;

/**
 * UserInfo取得API向けのinModelです。
 * 
 * @author nob
 */
@Data
public class OpFetchUserInfoInModel {

    /**
     * ユーザID
     */
    private String userId;
}
