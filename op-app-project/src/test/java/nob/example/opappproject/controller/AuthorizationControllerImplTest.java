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

import nob.example.opappproject.dto.CertificationInModel;
import nob.example.opappproject.dto.CertificationOutModel;
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

        mockMvc.perform(get("/api/op/authorization"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:http://localhost:3000/login"));
    }

    /**
     * certificateのテスト 正常系
     * 
     * @throws Exception
     */
    @Test
    public void test_certificate_success() throws Exception {

        // 入力値の作成
        CertificationInModel certificationInModel = new CertificationInModel();
        certificationInModel.setUserId("nob");
        certificationInModel.setPassword("p@ssw0rd");

        // モックレスポンス作成
        CertificationOutModel mockCertificationOutModel = new CertificationOutModel();
        mockCertificationOutModel.setIsCertificated(true);
        Mockito.when(authorizationService.certificate(certificationInModel)).thenReturn(mockCertificationOutModel);

        try {
            // サービス呼び出し
            CertificationOutModel certificationOutModel = authorizationService.certificate(certificationInModel);
            // 結果のassert
            assertEquals(true, certificationOutModel.getIsCertificated());

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    // TODO 異常系テスト
}
