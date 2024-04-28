package nob.example.rpappproject.rest.dto;

import lombok.Data;

/**
 * UserInfo取得API向けのレスポンスです。
 * 
 * @author nob
 */
@Data
public class OpFetchUserInfoResponse {

    /**
     * ユーザID
     */
    private String userId;

    /**
     * ユーザ名
     */
    private String userName;
}
