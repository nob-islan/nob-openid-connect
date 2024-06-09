package nob.example.opappproject.dto;

import lombok.Data;

/**
 * トークン発行API向けのinModelです。
 * 
 * @author nob
 */
@Data
public class IssueTokenInModel {

    /**
     * 認可コード
     */
    private String authorizationCode;

    /**
     * 検証用のランダム文字列
     */
    private String codeVerifier;
}
