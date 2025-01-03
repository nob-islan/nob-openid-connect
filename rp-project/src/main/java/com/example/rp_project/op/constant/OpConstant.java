package com.example.rp_project.op.constant;

/**
 * OpenIDプロバイダ向けの定数を管理するクラスです。
 * 
 * @author nob
 */
public class OpConstant {

    public static final String DOMAIN = "http://localhost:8081";

    /**
     * 認可リクエスト検証API
     */
    public static final String AUTHORIZE_API = DOMAIN + "/auth/authorization";

    /**
     * トークンリクエスト検証API
     */
    public static final String TOKEN_API = DOMAIN + "/auth/token";

    /**
     * クライアントID
     */
    public static final String CLIENT_ID = "first-client";

    /**
     * クライアントシークレット
     */
    public static final String CLIENT_SECRET = "123123123";

    /**
     * リダイレクトURI
     */
    public static final String REDIRECT_URI = "http://localhost:8080/login/token";

    /**
     * スコープ
     */
    public static final String SCOPE = "openid";

    /**
     * 認可リクエスト
     */
    public static final String AUTHORIZE_REQUEST = AUTHORIZE_API + "?clientId=" + CLIENT_ID + "&redirectUri="
            + REDIRECT_URI + "&scope=" + SCOPE;
}
