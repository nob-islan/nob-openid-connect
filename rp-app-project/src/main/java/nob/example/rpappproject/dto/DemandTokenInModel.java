package nob.example.rpappproject.dto;

import lombok.Data;

/**
 * アクセストークンリクエスト向けのinModelです。
 * 
 * @author nob
 */
@Data
public class DemandTokenInModel {

    /**
     * 認可コード
     */
    private String authorizationCode;

    /**
     * 検証用のランダム文字列
     */
    private String codeVerifier;
}
