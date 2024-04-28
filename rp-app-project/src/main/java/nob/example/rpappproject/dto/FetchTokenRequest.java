package nob.example.rpappproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * アクセストークン取得API向けのリクエストです。
 * 
 * @author nob
 */
@Data
@Schema(description = "アクセストークン取得API向けのリクエスト", type = "object")
public class FetchTokenRequest {

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

    /**
     * ユーザID
     */
    @Schema(description = "ユーザID", type = "string", example = "nob")
    private String userId;
}
