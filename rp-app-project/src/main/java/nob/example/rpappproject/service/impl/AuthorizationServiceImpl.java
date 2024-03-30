package nob.example.rpappproject.service.impl;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import nob.example.rpappproject.dto.DemandTokenInModel;
import nob.example.rpappproject.dto.DemandTokenOutModel;
import nob.example.rpappproject.dto.FetchUserInfoInModel;
import nob.example.rpappproject.dto.FetchUserInfoOutModel;
import nob.example.rpappproject.rest.constants.UrlConst;
import nob.example.rpappproject.rest.dto.OpFetchUserInfoRequest;
import nob.example.rpappproject.rest.dto.OpFetchUserInfoResponse;
import nob.example.rpappproject.rest.dto.OpIssueTokenRequest;
import nob.example.rpappproject.rest.dto.OpIssueTokenResponse;
import nob.example.rpappproject.service.AuthorizationService;

/**
 * 認証向けサービスの実装クラスです。
 * 
 * @author nob
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public DemandTokenOutModel demandToken(DemandTokenInModel demandTokenInModel) {

        // URL作成
        String urlStr = UrlConst.OP_APP_ORIGIN + UrlConst.BASE_URL + UrlConst.TOKEN;
        URI url = UriComponentsBuilder.fromHttpUrl(urlStr).build().toUri();

        // OP呼び出しのためのリクエスト作成
        OpIssueTokenRequest opIssueTokenRequest = new OpIssueTokenRequest();

        // OP トークン発行API呼び出し
        ResponseEntity<OpIssueTokenResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                new HttpEntity(opIssueTokenRequest, new HttpHeaders()), OpIssueTokenResponse.class);

        // TODO 返却値の作成
        DemandTokenOutModel demandTokenOutModel = new DemandTokenOutModel();

        return demandTokenOutModel;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public FetchUserInfoOutModel fetchUserInfo(FetchUserInfoInModel fetchUserInfoInModel) {

        // URL作成
        String urlStr = UrlConst.OP_APP_ORIGIN + UrlConst.BASE_URL + UrlConst.USERINFO;
        URI url = UriComponentsBuilder.fromHttpUrl(urlStr).build().toUri();

        // OP呼び出しのためのリクエストを作成
        OpFetchUserInfoRequest opFetchUserInfoRequest = new OpFetchUserInfoRequest();
        opFetchUserInfoRequest.setUserId(fetchUserInfoInModel.getUserId());

        // TODO GETメソッドの実装と併せて共通化検討

        // OP UserInfo取得API呼び出し
        ResponseEntity<OpFetchUserInfoResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                new HttpEntity(opFetchUserInfoRequest, new HttpHeaders()), OpFetchUserInfoResponse.class);

        // 返却値の作成
        FetchUserInfoOutModel fetchUserInfoOutModel = new FetchUserInfoOutModel();
        fetchUserInfoOutModel.setUserId(responseEntity.getBody().getUserId());
        fetchUserInfoOutModel.setUserName(responseEntity.getBody().getUserName());

        return fetchUserInfoOutModel;
    }
}
