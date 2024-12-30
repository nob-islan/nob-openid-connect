package com.example.rp_project.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.rp_project.exception.RpException;

import lombok.Data;

/**
 * RpExceptionのハンドラです。
 * 
 * @author nob
 */
@RestControllerAdvice
public class RpExceptionHandler {

    /**
     * RpExceptionが投げられた際に呼ばれるメソッドです。
     *
     * @param e
     * @return 例外メッセージ
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @ExceptionHandler(RpException.class)
    public ResponseEntity<RpExceptionResponseBody> rpExceptionHandle(RpException e) {

        RpExceptionResponseBody responseBody = new RpExceptionResponseBody();
        responseBody.setMessage(e.getMessage());

        return new ResponseEntity(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * RpException発生時のレスポンスボディです。
     *
     */
    @Data
    private class RpExceptionResponseBody {

        /**
         * エラーメッセージ
         */
        private String message;
    }
}
