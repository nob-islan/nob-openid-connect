package com.example.rp_project.op.reqres;

import lombok.Data;

/**
 * OpenIDプロバイダ向け トークン発行のリクエストモデルです。
 * 
 * @author nob
 */
@Data
public class OpFetchTokenRequest {

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
}
