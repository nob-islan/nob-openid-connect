package nob.example.rpappproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 認可エンドポイントへのリダイレクトAPI向けのリクエストです。
 * 
 * @author nob
 */
@Data
@Schema(description = "認可エンドポイントへのリダイレクトAPI向けのリクエスト", type = "object")
public class RedirectAuthorizationRequest {

    /**
     * リダイレクトURI
     */
    @Schema(description = "リダイレクトURI", type = "string", example = "http://example.nob/sample")
    private String redirectUri;
}
