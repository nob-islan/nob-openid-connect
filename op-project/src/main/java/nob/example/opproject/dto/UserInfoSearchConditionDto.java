package nob.example.opproject.dto;

import lombok.Data;

/**
 * ユーザ情報検索向けdtoです。
 * 
 * @author nob
 */
@Data
public class UserInfoSearchConditionDto {

    /** ログインID */
    private String loginId;
}
