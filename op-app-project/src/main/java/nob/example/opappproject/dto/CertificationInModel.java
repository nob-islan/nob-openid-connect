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
    @NotNull
    @Schema(description = "ユーザID", type = "string", example = "nob")
    private String userId;

    /**
     * パスワード
     */
    @NotNull
    @Schema(description = "パスワード", type = "string", example = "p@ssw0rd")
    private String password;
}