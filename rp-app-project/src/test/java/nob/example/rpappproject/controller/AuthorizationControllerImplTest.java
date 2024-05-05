package nob.example.rpappproject.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import nob.example.rpappproject.dto.DemandTokenInModel;
import nob.example.rpappproject.dto.DemandTokenOutModel;
import nob.example.rpappproject.dto.FetchTokenRequest;
import nob.example.rpappproject.dto.FetchTokenResponse;
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

    @MockBean
    private AuthorizationService authorizationService;

    /**
     * fetchTokenのテスト 正常系
     * 
     * @throws Exception
     */
    @Test
    public void test_fetchToken_success() throws Exception {

        // 入力値の作成
        FetchTokenRequest fetchTokenRequest = new FetchTokenRequest();
        fetchTokenRequest.setAuthorizationCode("testAuthorizationCode");
        fetchTokenRequest.setCodeVerifier("testCodeVerifier");

        // サービス呼び出し想定のinModel作成
        DemandTokenInModel demandTokenInModel = new DemandTokenInModel();
        demandTokenInModel.setAuthorizationCode("testAuthorizationCode");
        demandTokenInModel.setCodeVerifier("testCodeVerifier");

        // モックレスポンスの作成
        DemandTokenOutModel mockDemandTokenOutModel = new DemandTokenOutModel();
        mockDemandTokenOutModel.setAccessToken("testAccessToken");
        mockDemandTokenOutModel.setRefleshToken("testRefleshToken");
        mockDemandTokenOutModel.setIdToken("testIdToken");

        // サービスのモック化
        Mockito.when(authorizationService.demandToken(demandTokenInModel)).thenReturn(mockDemandTokenOutModel);

        try {
            // API呼び出し
            FetchTokenResponse fetchTokenResponse = authorizationController.fetchToken(fetchTokenRequest);
            // 結果のassert
            assertEquals("testAccessToken", fetchTokenResponse.getAccessToken());
            assertEquals("testRefleshToken", fetchTokenResponse.getRefleshToken());
            assertEquals("testIdToken", fetchTokenResponse.getIdToken());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
