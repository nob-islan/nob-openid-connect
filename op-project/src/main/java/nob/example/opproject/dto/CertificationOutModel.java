package nob.example.opproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 認証API向けのoutModelです。
 * 
 */
@Data
@Schema(description = "認証API向けのoutModel", type = "object")
public class CertificationOutModel {

    /**
     * 認証の成否
     */
    @Schema(description = "認証の成否", type = "boolean", example = "true")
    private Boolean isCertificated;
}
