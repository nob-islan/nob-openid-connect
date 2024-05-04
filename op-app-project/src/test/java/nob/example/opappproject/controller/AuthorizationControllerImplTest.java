package nob.example.opappproject.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import nob.example.opappproject.dto.CertificateRequest;
import nob.example.opappproject.dto.CertificateResponse;
import nob.example.opappproject.dto.AuthorizeRequest;
import nob.example.opappproject.dto.AuthorizeResponse;
import nob.example.opappproject.dto.CertificateInModel;
import nob.example.opappproject.dto.CertificateOutModel;
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

    /**
     * authorizeのテスト 正常系
     * 
     * @throws Exception
     */
    @Test
    public void test_authorize_success() throws Exception {

        // 入力値の作成
        AuthorizeRequest authorizeRequest = new AuthorizeRequest();
        authorizeRequest.setCodeChallenge("testCodeChallenge");
        authorizeRequest.setCodeChallengeMethod("S256");
        authorizeRequest.setRedirectUri("testRedirectUri");

        try {
            // API呼び出し
            AuthorizeResponse authorizeResponse = authorizationController.authorize(authorizeRequest);
            // 結果のassert
            assertEquals("testRedirectUri", authorizeResponse.getRedirectUri());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
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
        mockCertificateOutModel.setAuthorizationCode("testAuthorizationCode");
        mockCertificateOutModel.setRedirectUri("testRedirectUri");

        // サービスのモック化
        Mockito.when(authorizationService.certificate(certificateInModel)).thenReturn(mockCertificateOutModel);

        try {
            // API呼び出し
            CertificateResponse certificateResponse = authorizationController.certificate(certificateRequest);
            // 結果のassert
            assertEquals("testAuthorizationCode", certificateResponse.getAuthorizationCode());
            assertEquals("testRedirectUri", certificateResponse.getRedirectUri());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
    // TODO 異常系テスト
}
