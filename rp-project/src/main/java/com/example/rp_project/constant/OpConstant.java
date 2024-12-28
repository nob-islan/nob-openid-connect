package com.example.rp_project.constant;

/**
 * OpenIDプロバイダ向けの定数を管理するクラスです。
 * 
 * @author nob
 */
public class OpConstant {

    /**
     * OpenIDプロバイダの認可リクエスト検証API
     */
    public static final String AUTHORIZE_API = "http://localhost:8081/v1/api/auth/authorization?clientId=first-client&redirectUri=http://localhost:8080/auth/token";
}
