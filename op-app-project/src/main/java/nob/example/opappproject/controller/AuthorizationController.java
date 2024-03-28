package nob.example.opappproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nob.example.opappproject.constants.UrlConst;
import nob.example.opappproject.dto.CertificationInModel;
import nob.example.opappproject.dto.CertificationOutModel;
import nob.example.opappproject.dto.FetchUserInfoInModel;
import nob.example.opappproject.dto.FetchUserInfoOutModel;

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
     * 認可トークンを発行し、ログイン画面へリダイレクトします。
     * 
     * @return ログイン画面
     */
    @GetMapping(value = UrlConst.AUTHORIZATION)
    @Operation(summary = "認可", description = "${opapidoc.describe.authorization.authorize:説明文}")
    ModelAndView authorize();

    /**
     * ユーザID, パスワードによる認証を行います。
     * 
     * @param certificationInModel
     * @return 認証の結果
     */
    @PostMapping(value = UrlConst.CERTIFICATION)
    @Operation(summary = "認証", description = "${opapidoc.describe.authorization.certificate:説明文}")
    CertificationOutModel certificate(@RequestBody CertificationInModel certificationInModel);

    /**
     * UserInfoを取得します。
     * 
     * @param fetchUserInfoInModel
     * @return userInfo
     */
    @PostMapping(value = UrlConst.USERINFO)
    @Operation(summary = "UserInfo取得", description = "${opapidoc.describe.authorization.fetch-userinfo:説明文}")
    FetchUserInfoOutModel fetchUserInfo(@RequestBody FetchUserInfoInModel fetchUserInfoInModel);
}
