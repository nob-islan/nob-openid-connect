package nob.example.opappproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 認可API向けのレスポンスです。
 * 
 * @author nob
 */
@Data
@Schema(description = "認可API向けのレスポンス", type = "object")
public class AuthorizeResponse {

    /**
     * リダイレクトURI
     */
    @Schema(description = "リダイレクトURI", type = "string", example = "http://example.nob/sample")
    private String redirectUri;
}
