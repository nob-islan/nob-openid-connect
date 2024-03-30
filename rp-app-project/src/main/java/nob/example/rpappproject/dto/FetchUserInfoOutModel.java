package nob.example.rpappproject.dto;

import lombok.Data;

/**
 * UserInfo取得API向けのoutModelです。
 * 
 * @author nob
 */
@Data
public class FetchUserInfoOutModel {

    /**
     * ユーザID
     */
    private String userId;

    /**
     * ユーザ名
     */
    private String userName;
}
