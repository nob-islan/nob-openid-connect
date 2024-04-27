package nob.example.rpappproject.service.impl;

import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Random;

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
import nob.example.rpappproject.dto.RedirectAuthorizationOutModel;
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
     * codeVerifierの基となる文字
     */
    private String CODE_VERIFIER_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * codeVerifierの長さの最小値
     */
    private int CODE_VERIFIER_MIN_LENGTH = 60;

    /**
     * codeVerifieの長さの変化幅
     */
    private int CODE_VERIFIER_VARIATION_WIDTH = 10;

    /**
     * codeChallengeMethod: SHA256
     */
    private String CODE_CHALLENGE_METHOD = "S256";

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public RedirectAuthorizationOutModel redirectAuthorization() {

        // codeVerifierの文字列長を決定
        Random random = new Random();
        int codeVerifierLength = random.nextInt(CODE_VERIFIER_VARIATION_WIDTH) + CODE_VERIFIER_MIN_LENGTH;

        // codeVerifier生成
        StringBuilder codeVerifierBuilder = new StringBuilder();
        for (int i = 0; i < codeVerifierLength; i++) {
            int index = random.nextInt(CODE_VERIFIER_CHARACTERS.length());
            codeVerifierBuilder.append(CODE_VERIFIER_CHARACTERS.charAt(index));
        }
        String codeVerifier = codeVerifierBuilder.toString();

        // codeChallenge計算
        String codeChallenge = "";
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] sha256Byte = sha256.digest(codeVerifier.getBytes());
            HexFormat hex = HexFormat.of().withLowerCase();
            codeChallenge = hex.formatHex(sha256Byte);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); // TODO エラーハンドリング
        }

        // 返却値の作成
        RedirectAuthorizationOutModel redirectAuthorizationOutModel = new RedirectAuthorizationOutModel();
        redirectAuthorizationOutModel.setCodeVerifier(codeVerifier);
        redirectAuthorizationOutModel.setCodeChallenge(codeChallenge);
        redirectAuthorizationOutModel.setCodeChallengeMethod(CODE_CHALLENGE_METHOD);

        return redirectAuthorizationOutModel;
    }

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
        opIssueTokenRequest.setAuthorizationCode(demandTokenInModel.getAuthorizationCode());
        opIssueTokenRequest.setCodeVerifier(demandTokenInModel.getCodeVerifier());

        // OP トークン発行API呼び出し
        ResponseEntity<OpIssueTokenResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                new HttpEntity(opIssueTokenRequest, new HttpHeaders()), OpIssueTokenResponse.class);

        // 返却値の作成
        DemandTokenOutModel demandTokenOutModel = new DemandTokenOutModel();
        demandTokenOutModel.setAccessToken(responseEntity.getBody().getAccessToken());
        demandTokenOutModel.setRefleshToken(responseEntity.getBody().getRefleshToken());
        demandTokenOutModel.setIdToken(responseEntity.getBody().getIdToken());

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

        // OP UserInfo取得API呼び出し // TODO exchangeについて、GETメソッドの実装と併せて共通化検討
        ResponseEntity<OpFetchUserInfoResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                new HttpEntity(opFetchUserInfoRequest, new HttpHeaders()), OpFetchUserInfoResponse.class);

        // 返却値の作成
        FetchUserInfoOutModel fetchUserInfoOutModel = new FetchUserInfoOutModel();
        fetchUserInfoOutModel.setUserId(responseEntity.getBody().getUserId());
        fetchUserInfoOutModel.setUserName(responseEntity.getBody().getUserName());

        return fetchUserInfoOutModel;
    }
}
