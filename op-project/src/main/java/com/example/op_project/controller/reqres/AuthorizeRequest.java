package com.example.op_project.controller.reqres;

import lombok.Data;

/**
 * 認可のリクエストモデルです。
 * 
 * @author nob
 */
@Data
public class AuthorizeRequest {

    /**
     * クライアントID
     */
    private String clientId;

    /**
     * リダイレクトURI
     */
    private String redirectUri;

    // TODO codeVerifier対応
}
