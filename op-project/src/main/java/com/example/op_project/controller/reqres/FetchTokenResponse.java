package com.example.op_project.controller.reqres;

import lombok.Data;

/**
 * トークン発行のレスポンスモデルです。
 * 
 * @author nob
 */
@Data
public class FetchTokenResponse {

    /**
     * アクセストークン
     */
    private String accessToken;

    /**
     * IDトークン
     */
    private String idToken;
}
