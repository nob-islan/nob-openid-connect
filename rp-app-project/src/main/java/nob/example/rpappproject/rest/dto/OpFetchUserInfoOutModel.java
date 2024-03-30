package nob.example.rpappproject.rest.dto;

import lombok.Data;

/**
 * UserInfo取得API向けのoutModelです。
 * 
 * @author nob
 */
@Data
public class OpFetchUserInfoOutModel {

    /**
     * ユーザID
     */
    private String userId;

    /**
     * ユーザ名
     */
    private String userName;
}
