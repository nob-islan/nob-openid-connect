package com.example.op_project.controller.reqres;

import lombok.Data;

/**
 * トークン発行のリクエストモデルです。
 * 
 * @author nob
 */
@Data
public class FetchTokenRequest {

    /**
     * 認可コード
     */
    private String code;

    /**
     * クライアントID
     */
    private String clientId;

    /**
     * クライアントシークレット
     */
    private String clientSecret;

    /**
     * code_verifier
     */
    private String codeVerifier;
}
