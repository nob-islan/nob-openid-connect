package nob.example.rpappproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nob.example.rpappproject.constants.UrlConst;

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
     */
    @GetMapping(value = UrlConst.AUTHORIZATION_REDIRECT)
    @Operation(summary = "認可エンドポイントへのリダイレクト", description = "${rpapidoc.describe.authorization.redirect:説明文}")
    ModelAndView redirectAuthorization();
}
