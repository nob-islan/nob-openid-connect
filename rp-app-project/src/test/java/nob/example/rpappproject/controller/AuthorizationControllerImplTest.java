package nob.example.rpappproject.controller;

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
}
