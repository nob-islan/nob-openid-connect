package nob.example.opappproject.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import nob.example.opappproject.dto.CertificateRequest;
import nob.example.opappproject.dto.CertificateInModel;
import nob.example.opappproject.dto.CertificateOutModel;
import nob.example.opappproject.dto.CertificateResponse;
import nob.example.opappproject.service.AuthorizationService;

/**
 * AuthorizationControllerImplのテストクラスです。
 * 
 * @author nob
 */
@SpringBootTest
public class AuthorizationControllerImplTest {

    @Autowired
    private AuthorizationController authorizationController;

    @MockBean
    private AuthorizationService authorizationService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(authorizationController).build();
    }

    /**
     * authorizeのテスト 正常系
     * 
     * @throws Exception
     */
    @Test
    public void test_authorize_success() throws Exception {

        mockMvc.perform(get("/api/op/authorization?redirectUri=sample"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:http://localhost:3001/login?redirectUri=sample"));
    }

    /**
     * certificateのテスト 正常系
     * 
     * @throws Exception
     */
    @Test
    public void test_certificate_success() throws Exception {

        // 入力値の作成
        CertificateRequest certificateRequest = new CertificateRequest();
        certificateRequest.setUserId("testUserId");
        certificateRequest.setPassword("testPassword");
        certificateRequest.setRedirectUri("testRedirectUri");

        // サービス呼び出し想定のinModel作成
        CertificateInModel certificateInModel = new CertificateInModel();
        certificateInModel.setUserId("testUserId");
        certificateInModel.setPassword("testPassword");
        certificateInModel.setRedirectUri("testRedirectUri");

        // モックレスポンス作成
        CertificateOutModel mockCertificateOutModel = new CertificateOutModel();
        mockCertificateOutModel.setUserId("testUserId");
        mockCertificateOutModel.setAuthorizationCode("testAuthorizationCode");
        mockCertificateOutModel.setRedirectUri("testRedirectUri");

        // サービスのモック化
        Mockito.when(authorizationService.certificate(certificateInModel)).thenReturn(mockCertificateOutModel);

        try {
            // API呼び出し
            CertificateResponse certificateResponse = authorizationController.certificate(certificateRequest);
            // 結果のassert
            assertEquals("testUserId", certificateResponse.getUserId());
            assertEquals("testAuthorizationCode", certificateResponse.getAuthorizationCode());
            assertEquals("testRedirectUri", certificateResponse.getRedirectUri());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    // TODO 異常系テスト
}
