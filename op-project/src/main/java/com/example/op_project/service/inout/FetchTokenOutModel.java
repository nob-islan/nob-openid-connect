package com.example.op_project.service.inout;

import lombok.Data;

/**
 * トークン発行のoutModelです。
 * 
 * @author nob
 */
@Data
public class FetchTokenOutModel {

    /**
     * アクセストークン
     */
    private String accessToken;

    /**
     * IDトークン
     */
    private String idToken;
}
