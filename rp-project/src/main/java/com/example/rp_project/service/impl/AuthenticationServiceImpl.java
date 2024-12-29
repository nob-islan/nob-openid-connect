package com.example.rp_project.service.impl;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.rp_project.op.constant.OpUrlConstant;
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

    @SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
    @Override
    public FetchTokenOutModel fetchToken(FetchTokenInModel fetchTokenInModel) {

        // uri
        URI uri = UriComponentsBuilder.fromHttpUrl(OpUrlConstant.TOKEN_API).build().toUri();

        // リクエストモデル
        OpFetchTokenRequest opFetchTokenRequest = new OpFetchTokenRequest();

        // TODO OpenIDプロバイダのトークン発行API呼び出し
        ResponseEntity<OpFetchTokenResponse> opFetchTokenResponse = restTemplate.exchange(uri, HttpMethod.POST,
                new HttpEntity(opFetchTokenRequest, new HttpHeaders()), OpFetchTokenResponse.class);

        // TODO トークン検証

        // 返却値を作成
        FetchTokenOutModel fetchTokenOutModel = new FetchTokenOutModel();

        return fetchTokenOutModel;
    }
}
