package nob.example.rpappproject.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nob.example.rpappproject.constants.UrlConst;
import nob.example.rpappproject.dto.FetchTokenRequest;
import nob.example.rpappproject.dto.FetchTokenResponse;

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
     * アクセストークンを取得します。
     * 
     * @param fetchTokenRequest
     * @return アクセストークン
     */
    @PostMapping(value = UrlConst.TOKEN_FETCH)
    @Operation(summary = "アクセストークンの取得", description = "${rpapidoc.describe.authorization.fetch-token:説明文}")
    FetchTokenResponse fetchToken(@RequestBody FetchTokenRequest fetchTokenRequest);
}
