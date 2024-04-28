package nob.example.rpappproject.rest.dto;

import lombok.Data;

/**
 * UserInfo取得API向けのリクエストです。
 * 
 * @author nob
 */
@Data
public class OpFetchUserInfoRequest {

    /**
     * ユーザID
     */
    private String userId;
}
