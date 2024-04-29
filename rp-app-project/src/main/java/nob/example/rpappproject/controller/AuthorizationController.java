package nob.example.rpappproject.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import nob.example.rpappproject.constants.UrlConst;
import nob.example.rpappproject.dto.FetchTokenRequest;
import nob.example.rpappproject.dto.RedirectAuthorizationRequest;

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
     * 認可エンドポイントへのリダイレクトを行います。
     * 
     * @return 認証・ユーザ情報提供画面
     */
    @GetMapping(value = UrlConst.AUTHORIZATION)
    @Operation(summary = "認可エンドポイントへのリダイレクト", description = "${rpapidoc.describe.authorization.redirect-authorization:説明文}")
    ModelAndView redirectAuthorization(@ParameterObject RedirectAuthorizationRequest redirectAuthorizationRequest,
            HttpServletResponse httpServletResponse);

    /**
     * アクセストークンを取得します。
     * 
     * @param fetchTokenRequest
     * @return ログイン後トップ画面
     */
    @GetMapping(value = UrlConst.TOKEN_FETCH)
    @Operation(summary = "アクセストークンの取得", description = "${rpapidoc.describe.authorization.fetch-token:説明文}")
    ModelAndView fetchToken(@ParameterObject FetchTokenRequest fetchTokenRequest);
}
