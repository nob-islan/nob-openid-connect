package nob.example.rpappproject.dto;

import lombok.Data;

/**
 * アクセストークン要求向けのinModelです。
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
