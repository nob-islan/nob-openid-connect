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
import nob.example.rpappproject.rest.constants.UrlConst;
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
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public DemandTokenOutModel demandToken(DemandTokenInModel demandTokenInModel) {

        // URL作成
        String urlStr = UrlConst.OP_APP_ORIGIN + UrlConst.BASE_URL + UrlConst.TOKEN;
        URI url = UriComponentsBuilder.fromHttpUrl(urlStr).build().toUri();

        // OP呼び出しのためのリクエストを作成
        OpIssueTokenRequest opIssueTokenRequest = new OpIssueTokenRequest();
        opIssueTokenRequest.setAuthorizationCode(demandTokenInModel.getAuthorizationCode());
        opIssueTokenRequest.setCodeVerifier(demandTokenInModel.getCodeVerifier());

        // OP トークン発行API呼び出し // TODO restTemplate.exchange共通化検討
        ResponseEntity<OpIssueTokenResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                new HttpEntity(opIssueTokenRequest, new HttpHeaders()), OpIssueTokenResponse.class);

        // 返却値の作成
        DemandTokenOutModel demandTokenOutModel = new DemandTokenOutModel();
        demandTokenOutModel.setAccessToken(responseEntity.getBody().getAccessToken());
        demandTokenOutModel.setRefleshToken(responseEntity.getBody().getRefleshToken());
        demandTokenOutModel.setIdToken(responseEntity.getBody().getIdToken());

        return demandTokenOutModel;
    }
}
