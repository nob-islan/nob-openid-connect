package com.example.op_project.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.op_project.exception.OpSecurityException;

import lombok.Data;

/**
 * OpSecurityExceptionのハンドラです。
 *
 * @author nob
 */
@RestControllerAdvice
public class OpSecurityExceptionHandler {

    /**
     * OpSecurityExceptionが投げられた際に呼ばれるメソッドです。
     *
     * @param e
     * @return 例外メッセージ
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @ExceptionHandler(OpSecurityException.class)
    public ResponseEntity<OpSecurityExceptionResponseBody> sampleExceptionHandle(OpSecurityException e) {

        OpSecurityExceptionResponseBody responseBody = new OpSecurityExceptionResponseBody();
        responseBody.setMessage(e.getMessage());

        return new ResponseEntity(responseBody, HttpStatus.BAD_REQUEST);
    }

    /**
     * OpSecurityException発生時のレスポンスボディです。
     *
     */
    @Data
    private class OpSecurityExceptionResponseBody {

        /**
         * エラーメッセージ
         */
        private String message;
    }
}
