package com.example.op_project.service;

import org.springframework.stereotype.Service;

import com.example.op_project.exception.OpException;
import com.example.op_project.service.inout.AuthenticateInModel;
import com.example.op_project.service.inout.AuthenticateOutModel;
import com.example.op_project.service.inout.AuthorizeInModel;

/**
 * OpenIDプロバイダとしての認証を行うサービスインターフェースです。
 * 
 * @author nob
 */
@Service
public interface AuthenticationService {

    /**
     * 認可リクエストの検証を行います。
     * 
     * @param authorizeInModel 認可リクエスト
     */
    void authorize(AuthorizeInModel authorizeInModel) throws OpException;

    /**
     * 認証リクエストの検証を行います。
     * 
     * @param authenticateInModel 認証リクエスト
     * @return 認可コード
     */
    AuthenticateOutModel authenticate(AuthenticateInModel authenticateInModel) throws OpException;
}
