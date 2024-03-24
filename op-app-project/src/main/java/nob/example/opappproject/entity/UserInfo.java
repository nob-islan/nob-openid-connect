package nob.example.opappproject.entity;

import lombok.Data;

/**
 * user_infoテーブル向けエンティティクラスです。
 * 
 * @author nob
 */
@Data
public class UserInfo {

    /**
     * ユーザID
     */
    private String userId;

    /**
     * パスワード
     */
    private String password;

    /**
     * ユーザ名
     */
    private String userName;
}