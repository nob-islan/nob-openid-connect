package nob.example.rpappproject.service.impl;

import java.net.URI;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import nob.example.rpappproject.constants.JwtConst;
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

        // OP トークン発行API呼び出し
        ResponseEntity<OpIssueTokenResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                new HttpEntity(opIssueTokenRequest, new HttpHeaders()), OpIssueTokenResponse.class);

        // IDトークン検証
        Algorithm algorithm = Algorithm.HMAC256(JwtConst.SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedIdToken = verifier.verify(responseEntity.getBody().getIdToken());
        // issクレームがOPの識別子と一致すること
        if (!decodedIdToken.getIssuer().equals(JwtConst.ISSUER)) {
            System.out.println("認証失敗"); // TODO 例外作成
        }
        // audクレームがRPの識別子と一致すること
        if (!decodedIdToken.getAudience().get(0).equals(JwtConst.AUDIENCE)) {
            System.out.println("認証失敗"); // TODO 例外作成
        }
        // expクレームが現在時刻より後であること
        if (!decodedIdToken.getExpiresAt().after(new Date())) {
            System.out.println("認証失敗"); // TODO 例外作成
        }

        // 返却値の作成
        DemandTokenOutModel demandTokenOutModel = new DemandTokenOutModel();
        demandTokenOutModel.setAccessToken(responseEntity.getBody().getAccessToken());
        demandTokenOutModel.setIdToken(responseEntity.getBody().getIdToken());

        return demandTokenOutModel;
    }
}
