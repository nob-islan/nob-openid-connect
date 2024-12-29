package com.example.rp_project.op.reqres;

import lombok.Data;

/**
 * OpenIDプロバイダ向け トークン発行のレスポンスモデルです。
 * 
 * @author nob
 */
@Data
public class OpFetchTokenResponse {

    /**
     * アクセストークン
     */
    private String accessToken;

    /**
     * IDトークン
     */
    private String idToken;
}
