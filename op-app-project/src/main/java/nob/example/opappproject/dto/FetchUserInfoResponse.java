package nob.example.opappproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * UserInfo取得API向けのレスポンスです。
 * 
 * @author nob
 */
@Data
@Schema(description = "UserInfo取得API向けのレスポンス", type = "object")
public class FetchUserInfoResponse {

    /**
     * ユーザID
     */
    @Schema(description = "ユーザID", type = "string", example = "nob")
    private String userId;

    /**
     * ユーザ名
     */
    @Schema(description = "ユーザ名", type = "string", example = "nobuhiro")
    private String userName;
}
