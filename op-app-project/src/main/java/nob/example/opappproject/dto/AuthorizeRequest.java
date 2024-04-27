package nob.example.opappproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 認可API向けのリクエストです。
 * 
 * @author nob
 */
@Data
@Schema(description = "認可API向けのリクエスト", type = "object")
public class AuthorizeRequest {

    /**
     * codeVerifierをハッシュ化した値
     */
    @Schema(description = "codeVerifierをハッシュ化した値", type = "string", example = "abc123")
    private String codeChallenge;

    /**
     * codeVerifierのハッシュ化方式
     */
    @Schema(description = "codeVerifierのハッシュ化方式", type = "string", example = "S256")
    private String codeChallengeMethod;
}
