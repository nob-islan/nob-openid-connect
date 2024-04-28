package nob.example.rpappproject.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Random;

import org.springframework.stereotype.Service;

import nob.example.rpappproject.dto.RedirectAuthorizationOutModel;
import nob.example.rpappproject.service.AuthorizationService;

/**
 * 認証向けサービスの実装クラスです。
 * 
 * @author nob
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    /**
     * codeVerifierの基となる文字
     */
    private String CODE_VERIFIER_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * codeVerifierの長さの最小値
     */
    private int CODE_VERIFIER_MIN_LENGTH = 60;

    /**
     * codeVerifieの長さの変化幅
     */
    private int CODE_VERIFIER_VARIATION_WIDTH = 10;

    /**
     * codeChallengeMethod: SHA256
     */
    private String CODE_CHALLENGE_METHOD = "S256";

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public RedirectAuthorizationOutModel redirectAuthorization() {

        // codeVerifierの文字列長を決定
        Random random = new Random();
        int codeVerifierLength = random.nextInt(CODE_VERIFIER_VARIATION_WIDTH) + CODE_VERIFIER_MIN_LENGTH;

        // codeVerifier生成
        StringBuilder codeVerifierBuilder = new StringBuilder();
        for (int i = 0; i < codeVerifierLength; i++) {
            int index = random.nextInt(CODE_VERIFIER_CHARACTERS.length());
            codeVerifierBuilder.append(CODE_VERIFIER_CHARACTERS.charAt(index));
        }
        String codeVerifier = codeVerifierBuilder.toString();

        // codeChallenge計算
        String codeChallenge = "";
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] sha256Byte = sha256.digest(codeVerifier.getBytes());
            HexFormat hex = HexFormat.of().withLowerCase();
            codeChallenge = hex.formatHex(sha256Byte);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); // TODO エラーハンドリング
        }

        // 返却値の作成
        RedirectAuthorizationOutModel redirectAuthorizationOutModel = new RedirectAuthorizationOutModel();
        redirectAuthorizationOutModel.setCodeVerifier(codeVerifier);
        redirectAuthorizationOutModel.setCodeChallenge(codeChallenge);
        redirectAuthorizationOutModel.setCodeChallengeMethod(CODE_CHALLENGE_METHOD);

        return redirectAuthorizationOutModel;
    }
}
