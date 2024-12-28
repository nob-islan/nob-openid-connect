package com.example.op_project.service;

import org.springframework.stereotype.Service;

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
    void authorize(AuthorizeInModel authorizeInModel);
}
