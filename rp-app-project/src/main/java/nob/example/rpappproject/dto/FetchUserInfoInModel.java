package nob.example.rpappproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * ユーザ情報取得API向けのinModelです。
 * 
 * @author nob
 */
@Data
@Schema(description = "ユーザ情報取得API向けのinModel", type = "object")
public class FetchUserInfoInModel {

    /**
     * ユーザID
     */
    @NotNull
    @Schema(description = "ユーザID", type = "string", example = "nob")
    private String userId;
}
