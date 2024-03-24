package nob.example.opappproject.dto;

import lombok.Data;

/**
 * UserInfo検索向けdtoです。
 * 
 * @author nob
 */
@Data
public class UserInfoSearchConditionDto {

    /**
     * ユーザID
     */
    private String userId;
}