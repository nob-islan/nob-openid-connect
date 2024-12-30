package com.example.op_project.exception;

/**
 * OpenIDプロバイダとしてのビジネス例外クラスです。
 * 
 * @author nob
 */
public class OpBusinessException extends Exception {

    public OpBusinessException(String message) {
        super(message);
    }
}
