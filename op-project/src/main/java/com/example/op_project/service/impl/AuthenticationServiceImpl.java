package com.example.op_project.service.impl;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.op_project.exception.OpException;
import com.example.op_project.repository.AuthorizationInfoRepository;
import com.example.op_project.repository.ClientInfoRepository;
import com.example.op_project.repository.UserInfoRepository;
import com.example.op_project.repository.entity.AuthorizationInfo;
import com.example.op_project.repository.entity.ClientInfo;
import com.example.op_project.repository.entity.UserInfo;
import com.example.op_project.service.AuthenticationService;
import com.example.op_project.service.inout.AuthenticateInModel;
import com.example.op_project.service.inout.AuthenticateOutModel;
import com.example.op_project.service.inout.AuthorizeInModel;
import com.example.op_project.service.inout.FetchTokenInModel;
import com.example.op_project.service.inout.FetchTokenOutModel;

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

    @Autowired
    private AuthorizationInfoRepository authorizationInfoRepository;

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

        // 認可コードの有効期限[分]
        final Integer AUTHORIZATION_CODE_DEADLINE = 1;

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

        // 認可コード
        String code = stringBuilder.toString();

        // 認可コードをDBに保持
        AuthorizationInfo authorizationInfo = new AuthorizationInfo();
        authorizationInfo.setCode(code);
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MINUTE, AUTHORIZATION_CODE_DEADLINE); // 有効期限
        Date expirationDateTime = calendar.getTime();
        authorizationInfo.setExpirationDateTime(expirationDateTime);
        authorizationInfo.setIsDeleted(false);
        authorizationInfoRepository.save(authorizationInfo);

        // 返却値を作成
        AuthenticateOutModel authenticateOutModel = new AuthenticateOutModel();
        authenticateOutModel.setCode(code);

        return authenticateOutModel;
    }

    @Override
    public FetchTokenOutModel fetchToken(FetchTokenInModel fetchTokenInModel) throws OpException {

        // 検証用に認可コードを検索
        AuthorizationInfo authorizationInfo = authorizationInfoRepository.selectByCode(fetchTokenInModel.getCode());
        // 認可コードが取得できなければエラー
        if (authorizationInfo == null) {
            throw new OpException("認可コードが不正です。"); // TODO エラーメッセージを外出し
        }
        // 認可コードが取得できた時点で使用済みとする
        authorizationInfo.setIsDeleted(true);
        authorizationInfoRepository.save(authorizationInfo);
        // 認可コードの期限が切れていたらエラー
        Date now = new Date();
        if (now.after(authorizationInfo.getExpirationDateTime())) {
            throw new OpException("認可コードが不正です。"); // TODO エラーメッセージを外出し
        }

        // 検証用にクライアント情報を検索
        ClientInfo clientInfo = clientInfoRepository.selectByClientId(fetchTokenInModel.getClientId());
        // クライアント情報が取得できなければエラー
        if (clientInfo == null) {
            throw new OpException("クライアント情報が不正です。"); // TODO エラーメッセージを外出し
        }
        // クライアントシークレットが一致しなければエラー
        if (!clientInfo.getClientSecret().equals(fetchTokenInModel.getClientSecret())) {
            throw new OpException("クライアント情報が不正です。"); // TODO エラーメッセージを外出し
        }

        // TODO トークン発行
        String accessToken = "dummyToken";
        String idToken = "idToken";

        // 返却値を作成
        FetchTokenOutModel fetchTokenOutModel = new FetchTokenOutModel();
        fetchTokenOutModel.setAccessToken(accessToken);
        fetchTokenOutModel.setIdToken(idToken);

        return fetchTokenOutModel;
    }
}
