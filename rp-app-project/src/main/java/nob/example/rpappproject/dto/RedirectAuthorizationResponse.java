package nob.example.rpappproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 認可エンドポイントへのリダイレクトAPI向けのレスポンスです。
 * 
 * @author nob
 */
@Data
@Schema(description = "認可エンドポイントへのリダイレクトAPI向けのレスポンス", type = "object")
public class RedirectAuthorizationResponse {

    /**
     * codeVerifierをハッシュ化した値
     */
    @Schema(description = "codeVerifierをハッシュ化した値", type = "string", example = "xyz789")
    private String codeChallenge;

    /**
     * codeVerifierのハッシュ化方式
     */
    @Schema(description = "codeVerifierのハッシュ化方式", type = "string", example = "S256")
    private String codeChallengeMethod;

    /**
     * リダイレクトURI
     */
    @Schema(description = "リダイレクトURI", type = "string", example = "http://example.nob/sample")
    private String redirectUri;
}
