package nob.example.rpappproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.security.MessageDigest;
import java.util.HexFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import nob.example.rpappproject.dto.DemandTokenInModel;
import nob.example.rpappproject.dto.DemandTokenOutModel;
import nob.example.rpappproject.dto.FetchUserInfoInModel;
import nob.example.rpappproject.dto.FetchUserInfoOutModel;
import nob.example.rpappproject.dto.RedirectAuthorizationOutModel;
import nob.example.rpappproject.rest.dto.OpFetchUserInfoResponse;
import nob.example.rpappproject.rest.dto.OpIssueTokenResponse;

/**
 * AuthorizationServiceImplのテストクラスです。
 * 
 * @author nob
 */
@SpringBootTest
public class AuthorizationServiceImplTest {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * redirectAuthorizationのテスト 正常系
     * 
     * @throws Exception
     */
    @Test
    public void test_redirectAuthorization_success() throws Exception {

        try {
            // サービス呼び出し
            RedirectAuthorizationOutModel redirectAuthorizationOutModel = authorizationService.redirectAuthorization();
            // 結果のassert
            assertNotNull(redirectAuthorizationOutModel.getCodeVerifier());
            assertNotNull(redirectAuthorizationOutModel.getCodeChallenge());
            assertEquals(redirectAuthorizationOutModel.getCodeChallengeMethod(), "S256");
            // codeVerifierをハッシュ化して、codeChallengeを一致することを確認
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] sha256Byte = sha256.digest(redirectAuthorizationOutModel.getCodeVerifier().getBytes());
            HexFormat hex = HexFormat.of().withLowerCase();
            assertEquals(redirectAuthorizationOutModel.getCodeChallenge(), hex.formatHex(sha256Byte));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * demandTokenのテスト 正常系
     * 
     * @throws Exception
     */
    @Test
    public void test_demandToken_success() throws Exception {

        // 入力値の作成
        DemandTokenInModel demandTokenInModel = new DemandTokenInModel();
        demandTokenInModel.setAuthorizationCode("testAuthorizationCode");
        demandTokenInModel.setCodeVerifier("testCodeVerifier");

        // モック返却値の作成
        OpIssueTokenResponse opIssueTokenResponse = new OpIssueTokenResponse();
        opIssueTokenResponse.setAccessToken("testAccessToken");
        opIssueTokenResponse.setRefleshToken("testRefleshToken");
        opIssueTokenResponse.setIdToken("testIdToken");
        ObjectMapper mapper = new ObjectMapper();
        String response = mapper.writeValueAsString(opIssueTokenResponse);

        // リクエストURL
        String url = "http://localhost:8081/api/op/token";

        // モックサーバの作成
        MockRestServiceServer mockRestServiceServer = MockRestServiceServer.bindTo(restTemplate).build();
        mockRestServiceServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST)).andRespond(
                withSuccess(response, MediaType.APPLICATION_JSON));

        try {
            // サービス呼び出し
            DemandTokenOutModel demandTokenOutModel = authorizationService.demandToken(demandTokenInModel);
            // 結果のassert
            assertEquals("testAccessToken", demandTokenOutModel.getAccessToken());
            assertEquals("testRefleshToken", demandTokenOutModel.getRefleshToken());
            assertEquals("testIdToken", demandTokenOutModel.getIdToken());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * fetchUserInfoのテスト 正常系
     * 
     * @throws Exception
     */
    @Test
    public void test_fetchUserInfo_success() throws Exception {

        // 入力値の作成
        FetchUserInfoInModel fetchUserInfoInModel = new FetchUserInfoInModel();
        fetchUserInfoInModel.setUserId("testNob");

        // モック返却値の作成
        OpFetchUserInfoResponse opFetchUserInfoResponse = new OpFetchUserInfoResponse();
        opFetchUserInfoResponse.setUserId("testNob");
        opFetchUserInfoResponse.setUserName("testNobuhiro");
        ObjectMapper mapper = new ObjectMapper();
        String response = mapper.writeValueAsString(opFetchUserInfoResponse);

        // リクエストURL
        String url = "http://localhost:8081/api/op/userinfo";

        // モックサーバの作成
        MockRestServiceServer mockRestServiceServer = MockRestServiceServer.bindTo(restTemplate).build();
        mockRestServiceServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST)).andRespond(
                withSuccess(response, MediaType.APPLICATION_JSON));

        try {
            // サービス呼び出し
            FetchUserInfoOutModel fetchUserInfoOutModel = authorizationService.fetchUserInfo(fetchUserInfoInModel);
            // 結果のassert
            assertEquals("testNob", fetchUserInfoOutModel.getUserId());
            assertEquals("testNobuhiro", fetchUserInfoOutModel.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
