package com.example.op_project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.op_project.exception.OpException;
import com.example.op_project.repository.ClientInfoRepository;
import com.example.op_project.repository.entity.ClientInfo;
import com.example.op_project.service.AuthenticationService;
import com.example.op_project.service.inout.AuthorizeInModel;

/**
 * OpenIDプロバイダとしての認証を行うサービス実装です。
 * 
 * @author nob
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private ClientInfoRepository clientInfoRepository;

    @Override
    public void authorize(AuthorizeInModel authorizeInModel) throws OpException {

        // repository呼び出し
        ClientInfo clientInfo = clientInfoRepository.selectByKey(authorizeInModel.getClientId());

        // クライアント情報が取得できなければエラー
        if (clientInfo == null) {
            throw new OpException("クライアント情報が不正です。"); // TODO エラーメッセージを外出し
        }

        // リダイレクトURI検証
        if (!clientInfo.getRedirectUri().equals(authorizeInModel.getRedirectUri())) {
            throw new OpException("クライアント情報が不正です。"); // TODO エラーメッセージを外出し
        }
    }
}
