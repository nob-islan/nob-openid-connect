package nob.example.opappproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 認証API向けのレスポンスです。
 * 
 * @author nob
 */
@Data
@Schema(description = "認証API向けのレスポンス", type = "object")
public class CertificateResponse {

    /**
     * 認可コード
     */
    @Schema(description = "認可コード", type = "string", example = "abc123")
    private String authorizationCode;

    /**
     * リダイレクトURI
     */
    @Schema(description = "リダイレクトURI", type = "string", example = "http://example.nob/sample")
    private String redirectUri;
}
