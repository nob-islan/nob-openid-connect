package com.example.rp_project.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public FetchTokenOutModel fetchToken(FetchTokenInModel fetchTokenInModel) {

        // リクエストモデル
        OpFetchTokenRequest opFetchTokenRequest = new OpFetchTokenRequest();
        opFetchTokenRequest.setCode(fetchTokenInModel.getCode());
        opFetchTokenRequest.setClientId(OpUrlConstant.CLIENT_ID);
        opFetchTokenRequest.setClientSecret(OpUrlConstant.CLIENT_SECRET);

        // OpenIDプロバイダのトークン発行API呼び出し
        ResponseEntity<OpFetchTokenResponse> responseEntity = restTemplate.exchange(OpUrlConstant.TOKEN_API,
                HttpMethod.POST, new HttpEntity(opFetchTokenRequest, new HttpHeaders()), OpFetchTokenResponse.class);

        // TODO トークン検証

        // 返却値を作成
        FetchTokenOutModel fetchTokenOutModel = new FetchTokenOutModel();
        BeanUtils.copyProperties(responseEntity.getBody(), fetchTokenOutModel);

        return fetchTokenOutModel;
    }
}
