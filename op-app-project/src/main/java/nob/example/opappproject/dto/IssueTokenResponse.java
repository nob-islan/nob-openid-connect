package nob.example.opappproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * トークン発行API向けのレスポンスです。
 * 
 * @author nob
 */
@Data
@Schema(description = "トークン発行API向けのレスポンス", type = "object")
public class IssueTokenResponse {

    /**
     * アクセストークン
     */
    @Schema(description = "アクセストークン", type = "string", example = "abc123")
    private String accessToken;

    /**
     * リフレッシュトークン
     */
    @Schema(description = "リフレッシュトークン", type = "string", example = "abc123")
    private String refleshToken;

    /**
     * IDトークン
     */
    @Schema(description = "IDトークン", type = "string", example = "abc123")
    private String idToken;
}
