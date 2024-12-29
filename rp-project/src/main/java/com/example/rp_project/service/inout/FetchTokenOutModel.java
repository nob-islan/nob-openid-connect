package com.example.rp_project.service.inout;

import lombok.Data;

/**
 * トークン発行要求のoutModelです。
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
