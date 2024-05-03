package nob.example.rpappproject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import nob.example.rpappproject.dto.CalcCodeChallengeOutModel;
import nob.example.rpappproject.dto.DemandTokenInModel;
import nob.example.rpappproject.dto.DemandTokenOutModel;
import nob.example.rpappproject.dto.FetchTokenRequest;
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
        CalcCodeChallengeOutModel mockCalcCodeChallengeOutModel = new CalcCodeChallengeOutModel();
        mockCalcCodeChallengeOutModel.setCodeVerifier("testCodeVerifier");
        mockCalcCodeChallengeOutModel.setCodeChallenge("testCodeChallenge");
        mockCalcCodeChallengeOutModel.setCodeChallengeMethod("testCodeChallengeMethod");

        // サービスのモック化
        Mockito.when(authorizationService.redirectAuthorization()).thenReturn(mockCalcCodeChallengeOutModel);

        String expectedViewName = "redirect:http://localhost:8081/api/op/authorization"
                + "?codeChallenge=testCodeChallenge"
                + "&codeChallengeMethod=testCodeChallengeMethod"
                + "&redirectUri=sample";

        mockMvc.perform(get("/api/rp/authorization?redirectUri=sample"))
                .andExpect(status().isFound())
                .andExpect(view().name(expectedViewName));
    }

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

        String expectedViewName = "redirect:http://localhost:3000/top";

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/rp/token/fetch").content(objectMapper.writeValueAsString(fetchTokenRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isFound())
                .andExpect(flash().attribute("demandTokenOutModel", mockDemandTokenOutModel))
                .andExpect(view().name(expectedViewName));
    }
}
