package nob.example.rpappproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.security.MessageDigest;
import java.util.HexFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import nob.example.rpappproject.dto.RedirectAuthorizationOutModel;

/**
 * AuthorizationServiceImplのテストクラスです。
 * 
 * @author nob
 */
@SpringBootTest
public class AuthorizationServiceImplTest {

    @Autowired
    private AuthorizationService authorizationService;

    /**
     * redirectAuthorizationのテスト 正常系
     * 
     * @throws Exception
     */
    @Test
    public void test_redirectAuthorization_success() throws Exception {

        try {
            // サービス呼び出し
            RedirectAuthorizationOutModel redirectAuthorizationOutModel = authorizationService.redirectAuthorization();
            // 結果のassert
            assertNotNull(redirectAuthorizationOutModel.getCodeVerifier());
            assertNotNull(redirectAuthorizationOutModel.getCodeChallenge());
            assertEquals(redirectAuthorizationOutModel.getCodeChallengeMethod(), "S256");
            // codeVerifierをハッシュ化して、codeChallengeを一致することを確認
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] sha256Byte = sha256.digest(redirectAuthorizationOutModel.getCodeVerifier().getBytes());
            HexFormat hex = HexFormat.of().withLowerCase();
            assertEquals(redirectAuthorizationOutModel.getCodeChallenge(), hex.formatHex(sha256Byte));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
