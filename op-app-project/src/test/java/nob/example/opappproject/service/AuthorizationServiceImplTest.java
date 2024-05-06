package nob.example.opappproject.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import nob.example.opappproject.dto.AuthorizeInModel;
import nob.example.opappproject.dto.AuthorizeOutModel;
import nob.example.opappproject.dto.CertificateInModel;
import nob.example.opappproject.dto.CertificateOutModel;
import nob.example.opappproject.dto.RedirectUriSelectKey;
import nob.example.opappproject.dto.UserCredentialSelectKey;
import nob.example.opappproject.entity.ClientInfo;
import nob.example.opappproject.entity.UserInfo;
import nob.example.opappproject.repository.ClientInfoRepository;
import nob.example.opappproject.repository.UserInfoRepository;

/**
 * AuthorizationServiceImplのテストクラスです。
 * 
 * @author nob
 */
@SpringBootTest
public class AuthorizationServiceImplTest {

    @Autowired
    private AuthorizationService authorizationService;

    @MockBean
    private ClientInfoRepository clientInfoRepository;

    @MockBean
    private UserInfoRepository userInfoRepository;

    /**
     * authorizeのテスト 正常系
     * 
     * @throws Exception
     */
    @Test
    public void test_authorize_success() throws Exception {

        // 入力値の作成
        AuthorizeInModel authorizeInModel = new AuthorizeInModel();
        authorizeInModel.setClientId("testClientId");
        authorizeInModel.setRedirectUri("testRedirectUri");

        // repository呼び出し想定のkey作成
        RedirectUriSelectKey redirectUriSelectKey = new RedirectUriSelectKey();
        redirectUriSelectKey.setClientId("testClientId");

        // モックレスポンス作成
        ClientInfo mockClientInfo = new ClientInfo();
        mockClientInfo.setRedirectUri("testRedirectUri");
        List<ClientInfo> mockClientInfoList = new ArrayList<ClientInfo>();
        mockClientInfoList.add(mockClientInfo);

        // repositoryモック化
        Mockito.when(clientInfoRepository.selectRedirectUri(redirectUriSelectKey)).thenReturn(mockClientInfoList);

        try {
            // サービス呼び出し
            AuthorizeOutModel authorizeOutModel = authorizationService.authorize(authorizeInModel);
            // 結果のassert
            assertEquals("testRedirectUri", authorizeOutModel.getRedirectUri());
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
        CertificateInModel certificateInModel = new CertificateInModel();
        certificateInModel.setUserId("testUserId");
        certificateInModel.setPassword("testPassword");
        certificateInModel.setRedirectUri("testRedirectUri");

        // repository呼び出し想定のkey作成
        UserCredentialSelectKey userCredentialSelectKey = new UserCredentialSelectKey();
        userCredentialSelectKey.setUserId("testUserId");
        userCredentialSelectKey.setPassword("testPassword");

        // モックレスポンス作成
        UserInfo mockUserInfo = new UserInfo();
        mockUserInfo.setUserId("testUserId");
        List<UserInfo> mockUserInfoList = new ArrayList<UserInfo>();
        mockUserInfoList.add(mockUserInfo);

        // repositoryモック化
        Mockito.when(userInfoRepository.selectUserCredential(userCredentialSelectKey)).thenReturn(mockUserInfoList);

        try {
            // サービス呼び出し
            CertificateOutModel certificateOutModel = authorizationService.certificate(certificateInModel);
            // 結果のassert
            assertEquals("testAuthorizationCode", certificateOutModel.getAuthorizationCode());
            assertEquals("testRedirectUri", certificateOutModel.getRedirectUri());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
