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

import nob.example.rpappproject.dto.CalcCodeChallengeOutModel;
import nob.example.rpappproject.dto.DemandTokenInModel;
import nob.example.rpappproject.dto.DemandTokenOutModel;
import nob.example.rpappproject.dto.DemandUserInfoInModel;
import nob.example.rpappproject.dto.DemandUserInfoOutModel;
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
            CalcCodeChallengeOutModel calcCodeChallengeOutModel = authorizationService.redirectAuthorization();
            // 結果のassert
            assertNotNull(calcCodeChallengeOutModel.getCodeVerifier());
            assertNotNull(calcCodeChallengeOutModel.getCodeChallenge());
            assertEquals(calcCodeChallengeOutModel.getCodeChallengeMethod(), "S256");
            // codeVerifierをハッシュ化して、codeChallengeを一致することを確認
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] sha256Byte = sha256.digest(calcCodeChallengeOutModel.getCodeVerifier().getBytes());
            HexFormat hex = HexFormat.of().withLowerCase();
            assertEquals(calcCodeChallengeOutModel.getCodeChallenge(), hex.formatHex(sha256Byte));
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

        // モックレスポンスの作成
        OpIssueTokenResponse opIssueTokenResponse = new OpIssueTokenResponse();
        opIssueTokenResponse.setAccessToken("testAccessToken");
        opIssueTokenResponse.setRefleshToken("testRefleshToken");
        opIssueTokenResponse.setIdToken("testIdToken");
        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.writeValueAsString(opIssueTokenResponse);

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
     * demandUserInfoのテスト 正常系
     * 
     * @throws Exception
     */
    @Test
    public void test_demandUserInfo_success() throws Exception {

        // 入力値の作成
        DemandUserInfoInModel demandUserInfoInModel = new DemandUserInfoInModel();
        demandUserInfoInModel.setUserId("testUserId");

        // モックレスポンスの作成
        OpFetchUserInfoResponse opFetchUserInfoResponse = new OpFetchUserInfoResponse();
        opFetchUserInfoResponse.setUserId("testUserId");
        opFetchUserInfoResponse.setUserName("testUserName");
        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.writeValueAsString(opFetchUserInfoResponse);

        // リクエストURL
        String url = "http://localhost:8081/api/op/userinfo";

        // モックサーバの作成
        MockRestServiceServer mockRestServiceServer = MockRestServiceServer.bindTo(restTemplate).build();
        mockRestServiceServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST)).andRespond(
                withSuccess(response, MediaType.APPLICATION_JSON));

        try {
            // サービス呼び出し
            DemandUserInfoOutModel demandUserInfoOutModel = authorizationService.demandUserInfo(demandUserInfoInModel);
            // 結果のassert
            assertEquals("testUserId", demandUserInfoOutModel.getUserId());
            assertEquals("testUserName", demandUserInfoOutModel.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
