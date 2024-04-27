package nob.example.opappproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * トークン発行API向けのリクエストです。
 * 
 * @author nob
 */
@Data
@Schema(description = "トークン発行API向けのリクエスト", type = "object")
public class IssueTokenRequest {

    /**
     * 認可コード
     */
    @Schema(description = "認可コード", type = "string", example = "abc123")
    private String authorizationCode;

    /**
     * 検証用のランダム文字列
     */
    @Schema(description = "検証用のランダム文字列", type = "string", example = "abc123")
    private String codeVerifier;
}
