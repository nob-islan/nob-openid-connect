package com.example.op_project.exception;

/**
 * OpenIDプロバイダとしてのセキュリティ侵害の可能性がある例外クラスです。
 * 
 * @author nob
 */
public class OpSecurityException extends Exception {

    public OpSecurityException(String message) {
        super(message);
    }
}
