package nob.example.rpappproject.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import nob.example.rpappproject.dto.DemandTokenInModel;
import nob.example.rpappproject.dto.DemandTokenOutModel;
import nob.example.rpappproject.dto.DemandTokenRequest;
import nob.example.rpappproject.dto.DemandTokenResponse;
import nob.example.rpappproject.dto.FetchUserInfoInModel;
import nob.example.rpappproject.dto.FetchUserInfoOutModel;
import nob.example.rpappproject.dto.FetchUserInfoRequest;
import nob.example.rpappproject.dto.FetchUserInfoResponse;
import nob.example.rpappproject.dto.RedirectAuthorizationOutModel;
import nob.example.rpappproject.service.AuthorizationService;

/**
 * AuthorizationControllerImplのテストクラスです。
 * 
 * @author nob
 */
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorizationControllerImplTest {

    @Autowired
    private AuthorizationController authorizationController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorizationService authorizationService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(authorizationController).build();
    }

    /**
     * redirectAuthorizationのテスト 正常系
     * 
     * @throws Exception
     */
    @Test
    public void test_redirectAuthorization_success() throws Exception {

        // モック返却値の作成
        RedirectAuthorizationOutModel mockRedirectAuthorizationOutModel = new RedirectAuthorizationOutModel();
        mockRedirectAuthorizationOutModel.setCodeVerifier("abc123");
        mockRedirectAuthorizationOutModel.setCodeChallenge("xyz789");
        mockRedirectAuthorizationOutModel.setCodeChallengeMethod("S256");

        // サービスのモック化
        Mockito.when(authorizationService.redirectAuthorization()).thenReturn(mockRedirectAuthorizationOutModel);

        mockMvc.perform(get("/api/rp/authorization/redirect"))
                .andExpect(status().isFound())
                .andExpect(view().name(
                        "redirect:http://localhost:8081/api/op/authorization?codeChallenge=xyz789&codeChallengeMethod=S256"));
    }

    /**
     * demandTokenのテスト 正常系
     * 
     * @throws Exception
     */
    @Test
    public void test_demandToken_success() throws Exception {

        // 入力値の作成
        DemandTokenRequest demandTokenRequest = new DemandTokenRequest();
        demandTokenRequest.setAuthorizationCode("testAuthorizationCode");
        demandTokenRequest.setCodeVerifier("testCodeVerifier");

        // サービス呼び出し時の想定inModel
        DemandTokenInModel demandTokenInModel = new DemandTokenInModel();
        demandTokenInModel.setAuthorizationCode("testAuthorizationCode");
        demandTokenInModel.setCodeVerifier("testCodeVerifier");

        // モック返却値の作成
        DemandTokenOutModel demandTokenOutModel = new DemandTokenOutModel();
        demandTokenOutModel.setAccessToken("testAccessToken");
        demandTokenOutModel.setRefleshToken("testRefleshToken");
        demandTokenOutModel.setIdToken("testIdToken");

        // サービスのモック化
        Mockito.when(authorizationService.demandToken(demandTokenInModel)).thenReturn(demandTokenOutModel);

        try {
            // コントローラ呼び出し
            DemandTokenResponse demandTokenResponse = authorizationController.demandToken(demandTokenRequest);
            // 結果のassert
            assertEquals("testAccessToken", demandTokenResponse.getAccessToken());
            assertEquals("testRefleshToken", demandTokenResponse.getRefleshToken());
            assertEquals("testIdToken", demandTokenResponse.getIdToken());
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
        FetchUserInfoRequest fetchUserInfoRequest = new FetchUserInfoRequest();
        fetchUserInfoRequest.setUserId("testNob");

        // サービス呼び出し時の想定inModel
        FetchUserInfoInModel fetchUserInfoInModel = new FetchUserInfoInModel();
        fetchUserInfoInModel.setUserId("testNob");

        // モック返却値の作成
        FetchUserInfoOutModel mockFetchUserInfoOutModel = new FetchUserInfoOutModel();
        mockFetchUserInfoOutModel.setUserId("testNob");
        mockFetchUserInfoOutModel.setUserName("testNobuhiro");

        // サービスのモック化
        Mockito.when(authorizationService.fetchUserInfo(fetchUserInfoInModel)).thenReturn(mockFetchUserInfoOutModel);

        try {
            // コントローラ呼び出し
            FetchUserInfoResponse fetchUserInfoResponse = authorizationController.fetchUserInfo(fetchUserInfoRequest);
            // 結果のassert
            assertEquals("testNob", fetchUserInfoResponse.getUserId());
            assertEquals("testNobuhiro", fetchUserInfoResponse.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
