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

    // TODO リクエスト作成
}
