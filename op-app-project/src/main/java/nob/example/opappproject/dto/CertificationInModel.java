package nob.example.opappproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 認証API向けのinModelです。
 * 
 * @author nob
 */
@Data
@Schema(description = "認証API向けのinModel", type = "object")
public class CertificationInModel {

    /**
     * ユーザID
     */
    @Schema(description = "ユーザID", type = "string", example = "nob")
    @NotNull
    private String userId;

    /**
     * パスワード
     */
    @Schema(description = "パスワード", type = "string", example = "p@ssw0rd")
    @NotNull
    private String password;
}