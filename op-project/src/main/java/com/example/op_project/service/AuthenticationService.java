package com.example.op_project.service;

import org.springframework.stereotype.Service;

import com.example.op_project.exception.OpSecurityException;
import com.example.op_project.service.inout.AuthenticateInModel;
import com.example.op_project.service.inout.AuthenticateOutModel;
import com.example.op_project.service.inout.AuthorizeInModel;
import com.example.op_project.service.inout.FetchTokenInModel;
import com.example.op_project.service.inout.FetchTokenOutModel;

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
     * @throws OpSecurityException
     */
    void authorize(AuthorizeInModel authorizeInModel) throws OpSecurityException;

    /**
     * 認証リクエストの検証を行います。
     * 
     * @param authenticateInModel 認証リクエスト
     * @return 認可コード
     * @throws OpSecurityException
     */
    AuthenticateOutModel authenticate(AuthenticateInModel authenticateInModel) throws OpSecurityException;

    /**
     * トークンリクエストの検証を行います。
     * 
     * @param fetchTokenInModel トークンリクエスト
     * @return トークン
     * @throws OpSecurityException
     */
    FetchTokenOutModel fetchToken(FetchTokenInModel fetchTokenInModel) throws OpSecurityException;
}
