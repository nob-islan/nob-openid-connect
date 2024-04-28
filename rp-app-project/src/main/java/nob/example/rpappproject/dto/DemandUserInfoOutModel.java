package nob.example.rpappproject.dto;

import lombok.Data;

/**
 * ユーザ情報要求向けのoutModelです。
 * 
 * @author nob
 */
@Data
public class DemandUserInfoOutModel {

    /**
     * ユーザID
     */
    private String userId;

    /**
     * ユーザ名
     */
    private String userName;
}
