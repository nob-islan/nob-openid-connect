package com.example.op_project.service.inout;

import lombok.Data;

/**
 * 認可のinModelです。
 * 
 * @author nob
 */
@Data
public class AuthorizeInModel {

    /**
     * クライアントID
     */
    private String clientId;

    /**
     * リダイレクトURI
     */
    private String redirectUri;

    /**
     * スコープ
     */
    private String scope;

    /**
     * code_challenge
     */
    private String codeChallenge;

    /**
     * code_challenge_method
     */
    private String codeChallengeMethod;
}
