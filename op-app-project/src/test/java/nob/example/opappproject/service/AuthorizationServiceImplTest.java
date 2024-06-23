package nob.example.opappproject.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import nob.example.opappproject.constants.JwtConst;
import nob.example.opappproject.dto.AuthorizeInModel;
import nob.example.opappproject.dto.AuthorizeOutModel;
import nob.example.opappproject.dto.CertificateInModel;
import nob.example.opappproject.dto.CertificateOutModel;
import nob.example.opappproject.dto.IssueTokenInModel;
import nob.example.opappproject.dto.IssueTokenOutModel;
import nob.example.opappproject.dto.RedirectUriSelectKey;
import nob.example.opappproject.dto.UserCredentialSelectKey;
import nob.example.opappproject.entity.AuthorizationCodeInfo;
import nob.example.opappproject.entity.ClientInfo;
import nob.example.opappproject.entity.UserInfo;
import nob.example.opappproject.exceptions.OpAuthException;
import nob.example.opappproject.repository.AuthorizationCodeInfoRepository;
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

    @MockBean
    private AuthorizationCodeInfoRepository authorizationCodeInfoRepository;

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
     * authorizeのテスト リダイレクトURIが不正
     * 
     * @throws Exception
     */
    @Test
    public void test_authorize_invalid_redirectUri() throws Exception {

        // 入力値の作成
        AuthorizeInModel authorizeInModel = new AuthorizeInModel();
        authorizeInModel.setClientId("testClientId");
        authorizeInModel.setRedirectUri("testInvalidRedirectUri");

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
            authorizationService.authorize(authorizeInModel);
        } catch (OpAuthException e) {
            assertEquals("INVALID_REDIRECT_URI", e.getCode());
            assertEquals("認証に失敗しました。", e.getMessage());
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

        // repository呼び出し想定のkey作成 selectUserCredential
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
        doNothing().when(authorizationCodeInfoRepository).insert(any());

        try {
            // サービス呼び出し
            CertificateOutModel certificateOutModel = authorizationService.certificate(certificateInModel);
            // 結果のassert
            assertNotNull(certificateOutModel.getAuthorizationCode());
            assertEquals("testRedirectUri", certificateOutModel.getRedirectUri());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * certificateのテスト クレデンシャル情報が不正
     * 
     * @throws Exception
     */
    @Test
    public void test_certificate_invalidCredential() throws Exception {

        // 入力値の作成
        CertificateInModel certificateInModel = new CertificateInModel();
        certificateInModel.setUserId("testInvalidUserId");
        certificateInModel.setPassword("testPassword");
        certificateInModel.setRedirectUri("testRedirectUri");

        // repository呼び出し想定のkey作成 selectUserCredential
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
        doNothing().when(authorizationCodeInfoRepository).insert(any());

        try {
            // サービス呼び出し
            authorizationService.certificate(certificateInModel);
        } catch (OpAuthException e) {
            assertEquals("INVALID_CREDENTIAL", e.getCode());
            assertEquals("認証に失敗しました。", e.getMessage());
        }
    }

    /**
     * issueTokenのテスト 正常系
     * 
     * @throws Exception
     */
    @Test
    public void test_issueToken_success() throws Exception {

        // 入力値の作成
        IssueTokenInModel issueTokenInModel = new IssueTokenInModel();
        issueTokenInModel.setAuthorizationCode("testAuthorizationCode");
        issueTokenInModel.setCodeVerifier("testCodeVerifer");

        // モックレスポンス作成
        AuthorizationCodeInfo mockAuthorizationCodeInfo = new AuthorizationCodeInfo();
        mockAuthorizationCodeInfo.setUserId("testNob");
        mockAuthorizationCodeInfo.setCodeValue("testAuthorizationCode");
        mockAuthorizationCodeInfo.setCodeChallenge("b43a998c3ceeea516064cf9a4ec77c1a88a6bf9319aaa4ee474bca52ba80c1cd");
        List<AuthorizationCodeInfo> mockAuthorizationCodeInfoList = new ArrayList<AuthorizationCodeInfo>();
        mockAuthorizationCodeInfoList.add(mockAuthorizationCodeInfo);

        // repositoryモック化
        Mockito.when(authorizationCodeInfoRepository.selectAuthorizationCode(any()))
                .thenReturn(mockAuthorizationCodeInfoList);
        doNothing().when(authorizationCodeInfoRepository).updateIsDeleted(any());

        try {
            // サービス呼び出し
            IssueTokenOutModel issueTokenOutModel = authorizationService.issueToken(issueTokenInModel);
            // 結果のassert
            Algorithm algorithm = Algorithm.HMAC256(JwtConst.SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedAccessToken = verifier.verify(issueTokenOutModel.getAccessToken());
            assertNotNull(decodedAccessToken.getExpiresAt());
            assertNotNull(decodedAccessToken.getIssuedAt());
            assertEquals("nob-rp", decodedAccessToken.getAudience().get(0));
            assertEquals("nob-op", decodedAccessToken.getIssuer());
            assertEquals("testNob", decodedAccessToken.getSubject());
            DecodedJWT decodedIdToken = verifier.verify(issueTokenOutModel.getIdToken());
            assertNotNull(decodedIdToken.getExpiresAt());
            assertNotNull(decodedIdToken.getIssuedAt());
            assertEquals("nob-rp", decodedIdToken.getAudience().get(0));
            assertEquals("nob-op", decodedIdToken.getIssuer());
            assertEquals("testNob", decodedIdToken.getSubject());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * issueTokenのテスト 認可コードが不正
     * 
     * @throws Exception
     */
    @Test
    public void test_issueToken_invalidAuthorizationCode() throws Exception {

        // 入力値の作成
        IssueTokenInModel issueTokenInModel = new IssueTokenInModel();
        issueTokenInModel.setAuthorizationCode("testInvalidAuthorizationCode");
        issueTokenInModel.setCodeVerifier("testCodeVerifer");

        // モックレスポンス作成
        AuthorizationCodeInfo mockAuthorizationCodeInfo = new AuthorizationCodeInfo();
        mockAuthorizationCodeInfo.setUserId("testNob");
        mockAuthorizationCodeInfo.setCodeValue("testAuthorizationCode");
        mockAuthorizationCodeInfo.setCodeChallenge("b43a998c3ceeea516064cf9a4ec77c1a88a6bf9319aaa4ee474bca52ba80c1cd");
        List<AuthorizationCodeInfo> mockAuthorizationCodeInfoList = new ArrayList<AuthorizationCodeInfo>();
        mockAuthorizationCodeInfoList.add(mockAuthorizationCodeInfo);

        // repositoryモック化
        Mockito.when(authorizationCodeInfoRepository.selectAuthorizationCode(any()))
                .thenReturn(mockAuthorizationCodeInfoList);
        doNothing().when(authorizationCodeInfoRepository).updateIsDeleted(any());

        try {
            // サービス呼び出し
            authorizationService.issueToken(issueTokenInModel);
        } catch (OpAuthException e) {
            assertEquals("INVALID_AUTHORIZATION_CODE", e.getCode());
            assertEquals("認証に失敗しました。", e.getMessage());
        }
    }

    /**
     * issueTokenのテスト codeVerifierが不正
     * 
     * @throws Exception
     */
    @Test
    public void test_issueToken_invalidCodeVerifier() throws Exception {

        // 入力値の作成
        IssueTokenInModel issueTokenInModel = new IssueTokenInModel();
        issueTokenInModel.setAuthorizationCode("testAuthorizationCode");
        issueTokenInModel.setCodeVerifier("testInvalidCodeVerifer");

        // モックレスポンス作成
        AuthorizationCodeInfo mockAuthorizationCodeInfo = new AuthorizationCodeInfo();
        mockAuthorizationCodeInfo.setUserId("testNob");
        mockAuthorizationCodeInfo.setCodeValue("testAuthorizationCode");
        mockAuthorizationCodeInfo.setCodeChallenge("b43a998c3ceeea516064cf9a4ec77c1a88a6bf9319aaa4ee474bca52ba80c1cd");
        List<AuthorizationCodeInfo> mockAuthorizationCodeInfoList = new ArrayList<AuthorizationCodeInfo>();
        mockAuthorizationCodeInfoList.add(mockAuthorizationCodeInfo);

        // repositoryモック化
        Mockito.when(authorizationCodeInfoRepository.selectAuthorizationCode(any()))
                .thenReturn(mockAuthorizationCodeInfoList);
        doNothing().when(authorizationCodeInfoRepository).updateIsDeleted(any());

        try {
            // サービス呼び出し
            authorizationService.issueToken(issueTokenInModel);
        } catch (OpAuthException e) {
            assertEquals("INVALID_CODE_VERIFIER", e.getCode());
            assertEquals("認証に失敗しました。", e.getMessage());
        }
    }
}
