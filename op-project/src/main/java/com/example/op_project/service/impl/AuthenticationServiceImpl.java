package com.example.op_project.service.impl;

import org.springframework.stereotype.Service;

import com.example.op_project.service.AuthenticationService;
import com.example.op_project.service.inout.AuthorizeInModel;

/**
 * OpenIDプロバイダとしての認証を行うサービス実装です。
 * 
 * @author nob
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Override
    public void authorize(AuthorizeInModel authorizeInModel) {

        // TODO クライアントIDに紐づくリダイレクトURL検証
    }
}
