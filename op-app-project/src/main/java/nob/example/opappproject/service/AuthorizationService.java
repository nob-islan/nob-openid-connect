package nob.example.opappproject.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nob.example.opappproject.constants.UrlConst;
import nob.example.opappproject.dto.CertificationInModel;
import nob.example.opappproject.dto.CertificationOutModel;

/**
 * 認証向けサービスのインターフェースです。
 * 
 * @author nob
 */
@RestController
@RequestMapping(value = UrlConst.BASE_URL)
@Tag(name = "Authorization service", description = "認証向けAPIです。")
public interface AuthorizationService {

    /**
     * ユーザID, パスワードによる認証を行います。
     * 
     * @param certificationInModel
     * @return 認証の結果
     */
    @PostMapping(value = UrlConst.CERTIFICATION)
    @Operation(summary = "認証", description = "${opapidoc.describe.certificate:説明文}")
    CertificationOutModel certificate(@RequestBody CertificationInModel certificationInModel);
}
