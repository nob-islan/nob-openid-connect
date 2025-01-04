package com.example.op_project.constant;

/**
 * エラーメッセージを管理する定数クラスです。
 * 
 * @author nob
 */
public class ErrorMessageConstant {

    /**
     * スコープが不正な場合
     */
    public static final String INVALID_SCOPE = "スコープが不正です。";

    /**
     * クライアント情報が不正な場合
     */
    public static final String INVALID_CLIENT_INFO = "クライアント情報が不正です。";

    /**
     * ユーザ情報が不正な場合
     */
    public static final String INVALID_USER_INFO = "ユーザ情報が間違っています。";

    /**
     * code_challenge検証時にエラーが発生した場合
     */
    public static final String FAIL_CODE_CHALLENGE = "code_challengeの検証に失敗しました。";

    /**
     * 認可コードが不正な場合
     */
    public static final String INVALID_AUTHORIZATION_CODE = "認可コードが不正です。";
}
