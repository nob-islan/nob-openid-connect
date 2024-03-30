package nob.example.opappproject.dto;

import lombok.Data;

/**
 * UserInfo取得API向けのinModelです。
 * 
 * @author nob
 */
@Data
public class FetchUserInfoInModel {

    /**
     * ユーザID
     */
    private String userId;
}
