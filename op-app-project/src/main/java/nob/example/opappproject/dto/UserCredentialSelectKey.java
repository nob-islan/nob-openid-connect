package nob.example.opappproject.dto;

import lombok.Data;

/**
 * ユーザクレデンシャル取得の検索条件dtoです。
 * 
 * @author nob
 */
@Data
public class UserCredentialSelectKey {

    /**
     * ユーザID
     */
    private String userId;

    /**
     * パスワード
     */
    private String password;
}
