package nob.example.rpappproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * アクセストークン取得API向けのレスポンスです。
 * 
 * @author nob
 */
@Data
@Schema(description = "アクセストークン取得API向けのレスポンス", type = "object")
public class FetchTokenResponse {

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
