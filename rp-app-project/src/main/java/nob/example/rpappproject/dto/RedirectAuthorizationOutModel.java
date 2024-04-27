package nob.example.rpappproject.dto;

import lombok.Data;

/**
 * 認可エンドポイントへのリダイレクトAPI向けのoutModelです。
 * 
 * @author nob
 */
@Data
public class RedirectAuthorizationOutModel {

    /**
     * 検証用のランダム文字列
     */
    private String codeVerifier;

    /**
     * codeVerifierをハッシュ化した値
     */
    private String codeChallenge;

    /**
     * codeVerifierのハッシュ化方式
     */
    private String codeChallengeMethod;
}
