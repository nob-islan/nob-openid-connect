package nob.example.rpappproject.dto;

import lombok.Data;

/**
 * ユーザ情報を格納するdtoです。
 * 
 * @author nob
 */
@Data
public class UserInfoDto {

    /**
     * ユーザID
     */
    private String userId;

    /**
     * ユーザ名
     */
    private String userName;
}
