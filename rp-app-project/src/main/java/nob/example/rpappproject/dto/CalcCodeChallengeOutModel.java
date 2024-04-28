package nob.example.rpappproject.dto;

import lombok.Data;

/**
 * codeChallenge計算向けのoutModelです。
 * 
 * @author nob
 */
@Data
public class CalcCodeChallengeOutModel {

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
