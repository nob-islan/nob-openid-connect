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
     * サンプル例外が投げられた際に呼ばれるメソッドです。
     *
     * @param e
     * @return 例外メッセージ
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @ExceptionHandler(RpException.class)
    public ResponseEntity<RpExceptionHandlerResponseBody> rpExceptionHandle(RpException e) {

        RpExceptionHandlerResponseBody responseBody = new RpExceptionHandlerResponseBody();
        responseBody.setMessage(e.getMessage());

        return new ResponseEntity(responseBody, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * サンプル例外発生時のレスポンスボディです。
     *
     */
    @Data
    private class RpExceptionHandlerResponseBody {

        /**
         * エラーメッセージ
         */
        private String message;
    }
}
