package nob.example.opappproject.dto;

import lombok.Data;

/**
 * 画面に表示するユーザ情報の検索条件dtoです。
 * 
 * @author nob
 */
@Data
public class UserDataSelectKey {

    /**
     * ユーザID
     */
    private String userId;
}