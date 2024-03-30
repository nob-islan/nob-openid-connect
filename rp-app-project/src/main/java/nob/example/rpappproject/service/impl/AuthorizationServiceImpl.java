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

import nob.example.rpappproject.dto.FetchUserInfoInModel;
import nob.example.rpappproject.dto.FetchUserInfoOutModel;
import nob.example.rpappproject.rest.constants.UrlConst;
import nob.example.rpappproject.rest.dto.OpFetchUserInfoInModel;
import nob.example.rpappproject.rest.dto.OpFetchUserInfoOutModel;
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
    public FetchUserInfoOutModel fetchUserInfo(FetchUserInfoInModel fetchUserInfoInModel) {

        // URL作成
        String urlStr = UrlConst.OP_APP_ORIGIN + UrlConst.BASE_URL + UrlConst.USER_INFO;
        URI url = UriComponentsBuilder.fromHttpUrl(urlStr).build().toUri();

        // OP呼び出しのためのリクエストを作成
        OpFetchUserInfoInModel opFetchUserInfoInModel = new OpFetchUserInfoInModel();
        opFetchUserInfoInModel.setUserId(fetchUserInfoInModel.getUserId());

        // TODO GETメソッドの実装と併せて共通化検討

        // OP UserInfo取得API呼び出し
        ResponseEntity<OpFetchUserInfoOutModel> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                new HttpEntity(opFetchUserInfoInModel, new HttpHeaders()), OpFetchUserInfoOutModel.class);

        // 返却値の作成
        FetchUserInfoOutModel fetchUserInfoOutModel = new FetchUserInfoOutModel();
        fetchUserInfoOutModel.setUserId(responseEntity.getBody().getUserId());
        fetchUserInfoOutModel.setUserName(responseEntity.getBody().getUserName());

        return fetchUserInfoOutModel;
    }
}
