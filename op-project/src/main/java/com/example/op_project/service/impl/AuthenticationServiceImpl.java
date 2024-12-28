package com.example.op_project.service.impl;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.op_project.exception.OpException;
import com.example.op_project.repository.ClientInfoRepository;
import com.example.op_project.repository.UserInfoRepository;
import com.example.op_project.repository.entity.ClientInfo;
import com.example.op_project.repository.entity.UserInfo;
import com.example.op_project.service.AuthenticationService;
import com.example.op_project.service.inout.AuthenticateInModel;
import com.example.op_project.service.inout.AuthenticateOutModel;
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

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public void authorize(AuthorizeInModel authorizeInModel) throws OpException {

        // repository呼び出し
        ClientInfo clientInfo = clientInfoRepository.selectByClientId(authorizeInModel.getClientId());

        // クライアント情報が取得できなければエラー
        if (clientInfo == null) {
            throw new OpException("クライアント情報が不正です。"); // TODO エラーメッセージを外出し
        }

        // リダイレクトURI検証
        if (!clientInfo.getRedirectUri().equals(authorizeInModel.getRedirectUri())) {
            throw new OpException("クライアント情報が不正です。"); // TODO エラーメッセージを外出し
        }
    }

    @Override
    public AuthenticateOutModel authenticate(AuthenticateInModel authenticateInModel) throws OpException {

        // 認可コード向け文字列
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // ユーザ名、パスワード検証
        UserInfo userInfo = userInfoRepository.selectByUserInfo(authenticateInModel.getUsername(),
                authenticateInModel.getPassword());
        if (userInfo == null) {
            throw new OpException("ユーザ情報が間違っています。"); // TODO エラーメッセージを外出し
        }

        // 認可コード生成 // TODO 認可コードはランダム文字列でOKか再度確認
        Integer length = 32 + (int) (Math.random() * 9); // 暫定として32~40文字のランダム文字列とする
        StringBuilder stringBuilder = new StringBuilder(length);
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(randomIndex));
        }

        // 返却値作成
        AuthenticateOutModel authenticateOutModel = new AuthenticateOutModel();
        authenticateOutModel.setAuthorizationCode(stringBuilder.toString());

        return authenticateOutModel;
    }
}
