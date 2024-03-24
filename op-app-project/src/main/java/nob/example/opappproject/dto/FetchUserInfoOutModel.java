package nob.example.opappproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * UserInfo取得API向けのoutModelです。
 * 
 * @author nob
 */
@Data
@Schema(description = "UserInfo取得API向けのoutModel", type = "object")
public class FetchUserInfoOutModel {

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
