package nob.example.opproject.entity;

import lombok.Data;

/**
 * user_infoテーブル向けエンティティクラスです。
 * 
 * @author nob
 */
@Data
public class UserInfo {

    /** ユーザID */
    private String userId;

    /** ログインID */
    private String loginId;

    /** パスワード */
    private String password;

    /** ユーザ名 */
    private String userName;
}
