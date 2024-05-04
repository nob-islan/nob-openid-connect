package nob.example.opappproject.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nob.example.opappproject.constants.UrlConst;
import nob.example.opappproject.dto.AuthorizeRequest;
import nob.example.opappproject.dto.CertificateRequest;
import nob.example.opappproject.dto.CertificateResponse;
import nob.example.opappproject.dto.IssueTokenRequest;
import nob.example.opappproject.dto.IssueTokenResponse;

/**
 * 認証向けコントローラーのインターフェースです。
 * 
 * @author nob
 */
@RestController
@RequestMapping(value = UrlConst.BASE_URL)
@Tag(name = "Authorization", description = "認証向けAPIです。")
public interface AuthorizationController {

    /**
     * 認証・ユーザ情報提供画面へリダイレクトします。
     * 
     * @param authorizeRequest
     * @return 認証・ユーザ情報提供画面
     */
    @GetMapping(value = UrlConst.AUTHORIZATION)
    @Operation(summary = "認可", description = "${opapidoc.describe.authorization.authorize:説明文}")
    ModelAndView authorize(@ParameterObject AuthorizeRequest authorizeRequest);

    /**
     * ユーザID, パスワードによる認証を行い、認可コードを発行します。
     * 
     * @param certificateRequest
     * @return 認証の結果
     */
    @PostMapping(value = UrlConst.CERTIFICATION)
    @Operation(summary = "認証", description = "${opapidoc.describe.authorization.certificate:説明文}")
    CertificateResponse certificate(@RequestBody CertificateRequest certificateRequest);

    /**
     * アクセストークンを発行します。
     * 
     * @param issueTokenRequest
     * @return アクセストークン
     */
    @PostMapping(value = UrlConst.TOKEN)
    @Operation(summary = "トークン発行", description = "${opapidoc.describe.authorization.issue-token:説明文}")
    IssueTokenResponse issueToken(@RequestBody IssueTokenRequest issueTokenRequest);

}
