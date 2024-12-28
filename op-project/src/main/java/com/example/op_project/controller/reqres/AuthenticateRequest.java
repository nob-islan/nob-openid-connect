package com.example.op_project.controller.reqres;

import lombok.Data;

/**
 * 認証のリクエストモデルです。
 * 
 * @author nob
 */
@Data
public class AuthenticateRequest {

    /**
     * ユーザ名
     */
    private String username;

    /**
     * パスワード
     */
    private String password;
}
