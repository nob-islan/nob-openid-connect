package com.example.op_project.service.inout;

import lombok.Data;

/**
 * トークン発行のinModelです。
 * 
 * @author nob
 */
@Data
public class FetchTokenInModel {

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
