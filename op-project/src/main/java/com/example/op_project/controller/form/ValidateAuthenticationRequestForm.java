package com.example.op_project.controller.form;

import lombok.Data;

/**
 * ユーザID, パスワード入力時の情報を格納するformです。
 * 
 * @author nob
 */
@Data
public class ValidateAuthenticationRequestForm {

    /**
     * ユーザ名
     */
    private String username;

    /**
     * パスワード
     */
    private String password;
}
