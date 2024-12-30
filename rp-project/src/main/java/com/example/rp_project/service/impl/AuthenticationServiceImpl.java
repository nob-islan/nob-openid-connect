package com.example.rp_project.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.rp_project.op.constant.OpConstant;
import com.example.rp_project.op.reqres.OpFetchTokenRequest;
import com.example.rp_project.op.reqres.OpFetchTokenResponse;
import com.example.rp_project.service.AuthenticationService;
import com.example.rp_project.service.inout.FetchTokenInModel;
import com.example.rp_project.service.inout.FetchTokenOutModel;

/**
 * 認証のサービス実装です。
 * 
 * @author nob
 */
@Component
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private RestTemplate restTemplate;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public FetchTokenOutModel fetchToken(FetchTokenInModel fetchTokenInModel) {

        // トークンの複合キー
        final String DECODE_KEY = "testtesttest";

        // リクエストモデル
        OpFetchTokenRequest opFetchTokenRequest = new OpFetchTokenRequest();
        opFetchTokenRequest.setCode(fetchTokenInModel.getCode());
        opFetchTokenRequest.setClientId(OpConstant.CLIENT_ID);
        opFetchTokenRequest.setClientSecret(OpConstant.CLIENT_SECRET);

        // OpenIDプロバイダのトークン発行API呼び出し
        ResponseEntity<OpFetchTokenResponse> responseEntity = restTemplate.exchange(OpConstant.TOKEN_API,
                HttpMethod.POST, new HttpEntity(opFetchTokenRequest, new HttpHeaders()), OpFetchTokenResponse.class);

        // IDトークン検証
        DecodedJWT decodedJWT;
        Algorithm algorithm = Algorithm.HMAC256(DECODE_KEY);
        JWTVerifier verifier = JWT.require(algorithm).build();
        decodedJWT = verifier.verify(responseEntity.getBody().getAccessToken());
        boolean isValidToken = true;
        if (!decodedJWT.getIssuer().equals(OpConstant.DOMAIN)) {
            isValidToken = false;
        }
        if (!decodedJWT.getAudience().get(0).equals(OpConstant.CLIENT_ID)) {
            isValidToken = false;
        }

        // トークンが不正であればエラー
        if (!isValidToken) {
            throw new RuntimeException("トークンが不正です。"); // TODO エラーハンドリング
        }

        // 返却値を作成
        FetchTokenOutModel fetchTokenOutModel = new FetchTokenOutModel();
        BeanUtils.copyProperties(responseEntity.getBody(), fetchTokenOutModel);

        return fetchTokenOutModel;
    }
}
