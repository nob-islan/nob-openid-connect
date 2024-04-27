package nob.example.opappproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 認証API向けのレスポンスです。
 * 
 */
@Data
@Schema(description = "認証API向けのレスポンス", type = "object")
public class CertificationResponse {

    /**
     * 認可コード
     */
    @Schema(description = "認可コード", type = "boolean", example = "abc123")
    private String authorizationCode;
}
