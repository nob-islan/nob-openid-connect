package com.example.op_project.enums;

/**
 * scopeを列挙するenumクラスです。
 * 
 * @author nob
 */
public enum Scope {

    OPENID("openid", "OpenID Connect");

    /**
     * コード値
     */
    private String code;

    /**
     * 説明
     */
    private String explain;

    Scope(String code, String explain) {
        this.code = code;
        this.explain = explain;
    }

    public String getCode() {
        return code;
    }

    public String getExplain() {
        return explain;
    }
}
