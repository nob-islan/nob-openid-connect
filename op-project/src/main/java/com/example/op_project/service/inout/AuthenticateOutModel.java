package com.example.op_project.service.inout;

import lombok.Data;

/**
 * 認証のoutModelです。
 * 
 * @author nob
 */
@Data
public class AuthenticateOutModel {

    /**
     * 認可コード
     */
    private String authorizationCode;
}
