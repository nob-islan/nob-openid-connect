package com.example.op_project.service.inout;

import lombok.Data;

/**
 * 認証のinModelです。
 * 
 * @author nob
 */
@Data
public class AuthenticateInModel {

    /**
     * ユーザ名
     */
    private String username;

    /**
     * パスワード
     */
    private String password;

    /**
     * クライアントID
     */
    private String clientId;
}
