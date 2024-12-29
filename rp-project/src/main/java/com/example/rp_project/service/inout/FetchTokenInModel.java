package com.example.rp_project.service.inout;

import lombok.Data;

/**
 * トークン発行要求のinModelです。
 * 
 * @author nob
 */
@Data
public class FetchTokenInModel {

    /**
     * 認可コード
     */
    private String code;
}
