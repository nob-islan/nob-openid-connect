package nob.example.rpappproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

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
import nob.example.rpappproject.exceptions.RpAuthException;
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
        opIssueTokenResponse.setIdToken(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjI1ODI5ODQzOTgsImlhdCI6MTcxOTA3MDc5OCwiYXVkIjoibm9iLXJwIiwiaXNzIjoibm9iLW9wIiwic3ViIjoidGVzdE5vYiIsIm5vbmNlIjoidGVzdE5vbmNlIn0.VSA43whpqJ1wY4W3Dh_6lIfiMPi3TNuxn1djHj8XphU");
        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.writeValueAsString(opIssueTokenResponse);

        // リクエストURL
        String url = "http://op-app:8081/api/op/token";

        // モックサーバの作成
        MockRestServiceServer mockRestServiceServer = MockRestServiceServer.bindTo(restTemplate).build();
        mockRestServiceServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST)).andRespond(
                withSuccess(response, MediaType.APPLICATION_JSON));

        try {
            // サービス呼び出し
            DemandTokenOutModel demandTokenOutModel = authorizationService.demandToken(demandTokenInModel);
            // 結果のassert
            assertEquals("testAccessToken", demandTokenOutModel.getAccessToken());
            assertEquals(
                    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjI1ODI5ODQzOTgsImlhdCI6MTcxOTA3MDc5OCwiYXVkIjoibm9iLXJwIiwiaXNzIjoibm9iLW9wIiwic3ViIjoidGVzdE5vYiIsIm5vbmNlIjoidGVzdE5vbmNlIn0.VSA43whpqJ1wY4W3Dh_6lIfiMPi3TNuxn1djHj8XphU",
                    demandTokenOutModel.getIdToken());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * demandTokenのテスト issuerが不正
     * 
     * @throws Exception
     */
    @Test
    public void test_demandToken_invalidIssuer() throws Exception {

        // 入力値の作成
        DemandTokenInModel demandTokenInModel = new DemandTokenInModel();
        demandTokenInModel.setAuthorizationCode("testAuthorizationCode");
        demandTokenInModel.setCodeVerifier("testCodeVerifier");

        // モックレスポンスの作成
        OpIssueTokenResponse opIssueTokenResponse = new OpIssueTokenResponse();
        opIssueTokenResponse.setAccessToken("testAccessToken");
        opIssueTokenResponse.setIdToken(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MTkyMDIzNTIsImlhdCI6MTcxOTExNTk1MiwiYXVkIjoibm9iLXJwIiwiaXNzIjoiaW52YWxpZC1ub2Itb3AiLCJzdWIiOiJ0ZXN0Tm9iIn0.toczPUpA4iXY7yVhu7obOTqAfxyZr_-b8F4TPfqKhvA");
        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.writeValueAsString(opIssueTokenResponse);

        // リクエストURL
        String url = "http://op-app:8081/api/op/token";

        // モックサーバの作成
        MockRestServiceServer mockRestServiceServer = MockRestServiceServer.bindTo(restTemplate).build();
        mockRestServiceServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST)).andRespond(
                withSuccess(response, MediaType.APPLICATION_JSON));

        try {
            // サービス呼び出し
            authorizationService.demandToken(demandTokenInModel);
        } catch (RpAuthException e) {
            assertEquals("INVALID_ID_TOKEN", e.getCode());
            assertEquals("認証に失敗しました。", e.getMessage());
        }
    }

    /**
     * demandTokenのテスト audienceが不正
     * 
     * @throws Exception
     */
    @Test
    public void test_demandToken_invalidAudience() throws Exception {

        // 入力値の作成
        DemandTokenInModel demandTokenInModel = new DemandTokenInModel();
        demandTokenInModel.setAuthorizationCode("testAuthorizationCode");
        demandTokenInModel.setCodeVerifier("testCodeVerifier");

        // モックレスポンスの作成
        OpIssueTokenResponse opIssueTokenResponse = new OpIssueTokenResponse();
        opIssueTokenResponse.setAccessToken("testAccessToken");
        opIssueTokenResponse.setIdToken(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MTkyMDI0MzcsImlhdCI6MTcxOTExNjAzNywiYXVkIjoiaW52YWxpZC1ub2ItcnAiLCJpc3MiOiJub2Itb3AiLCJzdWIiOiJ0ZXN0Tm9iIn0.ou8vX4mhiyus1NQ_seK9zB-tqRD7U3BEglFFulMXj94");
        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.writeValueAsString(opIssueTokenResponse);

        // リクエストURL
        String url = "http://op-app:8081/api/op/token";

        // モックサーバの作成
        MockRestServiceServer mockRestServiceServer = MockRestServiceServer.bindTo(restTemplate).build();
        mockRestServiceServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST)).andRespond(
                withSuccess(response, MediaType.APPLICATION_JSON));

        try {
            // サービス呼び出し
            authorizationService.demandToken(demandTokenInModel);
        } catch (RpAuthException e) {
            assertEquals("INVALID_ID_TOKEN", e.getCode());
            assertEquals("認証に失敗しました。", e.getMessage());
        }
    }

    /**
     * demandTokenのテスト expiresAtが不正
     * 
     * @throws Exception
     */
    @Test
    public void test_demandToken_invalidExpiresAt() throws Exception {

        // 入力値の作成
        DemandTokenInModel demandTokenInModel = new DemandTokenInModel();
        demandTokenInModel.setAuthorizationCode("testAuthorizationCode");
        demandTokenInModel.setCodeVerifier("testCodeVerifier");

        // モックレスポンスの作成
        OpIssueTokenResponse opIssueTokenResponse = new OpIssueTokenResponse();
        opIssueTokenResponse.setAccessToken("testAccessToken");
        opIssueTokenResponse.setIdToken(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MTgyNTIwOTAsImlhdCI6MTcxOTExNjA5MCwiYXVkIjoibm9iLXJwIiwiaXNzIjoibm9iLW9wIiwic3ViIjoidGVzdE5vYiJ9.BC_Xua-PgRVsNENX5CkiBep08Am_13g87F825z2hoPQ");
        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.writeValueAsString(opIssueTokenResponse);

        // リクエストURL
        String url = "http://op-app:8081/api/op/token";

        // モックサーバの作成
        MockRestServiceServer mockRestServiceServer = MockRestServiceServer.bindTo(restTemplate).build();
        mockRestServiceServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST)).andRespond(
                withSuccess(response, MediaType.APPLICATION_JSON));

        try {
            // サービス呼び出し
            authorizationService.demandToken(demandTokenInModel);
        } catch (RpAuthException e) {
            assertEquals("INVALID_ID_TOKEN", e.getCode());
            assertEquals("認証に失敗しました。", e.getMessage());
        }
    }
}
