package com.example.rp_project.controller.reqres;

import lombok.Data;

/**
 * トークン発行要求のリクエストモデルです。
 * 
 * @author nob
 */
@Data
public class FetchTokenRequest {

    /**
     * 認可コード
     */
    private String code;

    // TODO クライアントID

    // TODO クライアントシークレット
}
