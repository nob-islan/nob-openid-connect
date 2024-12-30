package com.example.op_project.service.impl;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.op_project.constant.ErrorMessageConstant;
import com.example.op_project.exception.OpException;
import com.example.op_project.repository.AuthorizationInfoRepository;
import com.example.op_project.repository.ClientInfoRepository;
import com.example.op_project.repository.TokenManagementRepository;
import com.example.op_project.repository.UserInfoRepository;
import com.example.op_project.repository.entity.AuthorizationInfo;
import com.example.op_project.repository.entity.ClientInfo;
import com.example.op_project.repository.entity.TokenManagement;
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

    @Autowired
    private TokenManagementRepository tokenManagementRepository;

    @Value("${token.issuer}")
    private String tokenIssuer;

    @Override
    public void authorize(AuthorizeInModel authorizeInModel) throws OpException {

        // repository呼び出し
        ClientInfo clientInfo = clientInfoRepository.selectByClientId(authorizeInModel.getClientId());

        // クライアント情報が取得できなければエラー
        if (clientInfo == null) {
            throw new OpException(ErrorMessageConstant.INVALID_CLIENT_INFO);
        }

        // リダイレクトURI検証
        if (!clientInfo.getRedirectUri().equals(authorizeInModel.getRedirectUri())) {
            throw new OpException(ErrorMessageConstant.INVALID_CLIENT_INFO);
        }
    }

    @Override
    public AuthenticateOutModel authenticate(AuthenticateInModel authenticateInModel) throws OpException {

        // 認可コード向け文字列
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        // 認可コードの有効期限[分]
        final Integer AUTHORIZATION_CODE_DEADLINE = 1;
        // 認可コードの長さの最小値
        final Integer MIN_AUTHORIZATION_CODE_LENGTH = 20;
        // 認可コードの長さの幅
        final Integer AUTHORIZATION_CODE_LENGTH_RANGE = 10;

        // ユーザ名、パスワード検証
        UserInfo userInfo = userInfoRepository.selectByUserInfo(authenticateInModel.getUsername(),
                authenticateInModel.getPassword());
        if (userInfo == null) {
            throw new OpException(ErrorMessageConstant.INVALID_USER_INFO);
        }

        // 認可コード生成
        Integer length = MIN_AUTHORIZATION_CODE_LENGTH + (int) (Math.random() * AUTHORIZATION_CODE_LENGTH_RANGE);
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
        authorizationInfo.setUsername(authenticateInModel.getUsername());
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MINUTE, AUTHORIZATION_CODE_DEADLINE);
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

        // 認可コードの有効期限[分]
        final Integer ACCESS_TOKEN_DEADLINE = 10;

        // 検証用に認可コードを検索
        AuthorizationInfo authorizationInfo = authorizationInfoRepository.selectByCode(fetchTokenInModel.getCode());
        // 認可コードが取得できなければエラー
        if (authorizationInfo == null) {
            throw new OpException(ErrorMessageConstant.INVALID_AUTHORIZATION_CODE);
        }
        // 認可コードが取得できた時点で使用済みとする
        authorizationInfo.setIsDeleted(true);
        authorizationInfoRepository.save(authorizationInfo);
        // 認可コードの期限が切れていたらエラー
        Date now = new Date();
        if (now.after(authorizationInfo.getExpirationDateTime())) {
            throw new OpException(ErrorMessageConstant.INVALID_AUTHORIZATION_CODE);
        }

        // 検証用にクライアント情報を検索
        ClientInfo clientInfo = clientInfoRepository.selectByClientId(fetchTokenInModel.getClientId());
        // クライアント情報が取得できなければエラー
        if (clientInfo == null) {
            throw new OpException(ErrorMessageConstant.INVALID_CLIENT_INFO);
        }
        // クライアントシークレットが一致しなければエラー
        if (!clientInfo.getClientSecret().equals(fetchTokenInModel.getClientSecret())) {
            throw new OpException(ErrorMessageConstant.INVALID_CLIENT_INFO);
        }

        // トークン生成向けの情報を検索
        TokenManagement tokenManagement = tokenManagementRepository.selectByClientId(fetchTokenInModel.getClientId());

        // トークンの有効期限を設定
        now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MINUTE, ACCESS_TOKEN_DEADLINE);
        Date expirationDateTime = calendar.getTime();

        // トークンを生成
        Algorithm algorithm = Algorithm.HMAC256(tokenManagement.getHmacKey());
        String accessToken = JWT.create()
                .withExpiresAt(expirationDateTime) // トークンの有効期間終了時間
                .withIssuedAt(now) // 発行日時
                .withAudience(fetchTokenInModel.getClientId()) // トークンの利用者
                .withIssuer(tokenIssuer) // トークン発行者情報外だし
                .withSubject(authorizationInfo.getUsername()) // アクセス主体
                .sign(algorithm);
        String idToken = JWT.create()
                .withExpiresAt(expirationDateTime) // トークンの有効期間終了時間
                .withIssuedAt(now) // 発行日時
                .withAudience(fetchTokenInModel.getClientId()) // トークンの利用者
                .withIssuer(tokenIssuer) // トークン発行者情報外だし
                .withSubject(authorizationInfo.getUsername()) // アクセス主体
                .sign(algorithm);

        // 返却値を作成
        FetchTokenOutModel fetchTokenOutModel = new FetchTokenOutModel();
        fetchTokenOutModel.setAccessToken(accessToken);
        fetchTokenOutModel.setIdToken(idToken);

        return fetchTokenOutModel;
    }
}
