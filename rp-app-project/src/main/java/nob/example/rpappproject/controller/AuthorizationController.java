package nob.example.rpappproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nob.example.rpappproject.constants.UrlConst;
import nob.example.rpappproject.dto.DemandTokenRequest;
import nob.example.rpappproject.dto.DemandTokenResponse;
import nob.example.rpappproject.dto.FetchUserInfoRequest;
import nob.example.rpappproject.dto.FetchUserInfoResponse;

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
    @GetMapping(value = UrlConst.AUTHORIZATION_REDIRECT)
    @Operation(summary = "認可エンドポイントへのリダイレクト", description = "${rpapidoc.describe.authorization.redirect-authorization:説明文}")
    ModelAndView redirectAuthorization();

    /**
     * アクセストークンおよびIDトークンを要求します。
     * 
     * @param demandTokenRequest
     * @return OPから発行されたトークン
     */
    @PostMapping(value = UrlConst.TOKEN_DEMAND)
    @Operation(summary = "アクセストークンおよびIDトークンの要求", description = "${rpapidoc.describe.authorization.demand-token:説明文}")
    DemandTokenResponse demandToken(@RequestBody DemandTokenRequest demandTokenRequest);

    /**
     * ユーザ情報を取得します。
     * 
     * @param fetchUserInfoInModel
     * @return OP上のユーザ情報
     */
    @PostMapping(value = UrlConst.USERINFO_FETCH)
    @Operation(summary = "ユーザ情報の取得", description = "${rpapidoc.describe.authorization.fetch-userinfo:説明文}")
    FetchUserInfoResponse fetchUserInfo(@RequestBody FetchUserInfoRequest fetchUserInfoRequest);
}
