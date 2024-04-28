package nob.example.rpappproject.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import nob.example.rpappproject.controller.AuthorizationController;
import nob.example.rpappproject.dto.DemandTokenInModel;
import nob.example.rpappproject.dto.DemandTokenOutModel;
import nob.example.rpappproject.dto.DemandUserInfoInModel;
import nob.example.rpappproject.dto.DemandUserInfoOutModel;
import nob.example.rpappproject.dto.FetchTokenRequest;
import nob.example.rpappproject.dto.RedirectAuthorizationOutModel;
import nob.example.rpappproject.rest.constants.UrlConst;
import nob.example.rpappproject.service.AuthorizationService;

/**
 * 認証向けコントローラーの実装クラスです。
 * 
 * @author nob
 */
@RestController
public class AuthorizationControllerImpl implements AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public ModelAndView redirectAuthorization() {

        // サービスを呼び出してcodeChallengeを計算
        RedirectAuthorizationOutModel redirectAuthorizationOutModel = authorizationService.redirectAuthorization();

        // TODO codeVerifierを保持（httpSession?）

        // クエリパラメータを作成
        String queryParam = "?" + "codeChallenge=" + redirectAuthorizationOutModel.getCodeChallenge()
                + "&codeChallengeMethod=" + redirectAuthorizationOutModel.getCodeChallengeMethod();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(
                "redirect:" + UrlConst.OP_APP_ORIGIN + UrlConst.BASE_URL + UrlConst.AUTHORIZATION + queryParam);

        return modelAndView;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public ModelAndView fetchToken(FetchTokenRequest fetchTokenRequest) {

        // トークンリクエスト
        DemandTokenInModel demandTokenInModel = new DemandTokenInModel();
        demandTokenInModel.setAuthorizationCode(fetchTokenRequest.getAuthorizationCode());
        demandTokenInModel.setCodeVerifier(fetchTokenRequest.getCodeVerifier());
        DemandTokenOutModel demandTokenOutModel = authorizationService.demandToken(demandTokenInModel);

        // TODO IDトークン検証

        // ユーザ情報リクエスト // TODO アクセストークンを付与してコール
        DemandUserInfoInModel demandUserInfoInModel = new DemandUserInfoInModel();
        demandUserInfoInModel.setUserId(fetchTokenRequest.getUserId());
        DemandUserInfoOutModel demandUserInfoOutModel = authorizationService.demandUserInfo(demandUserInfoInModel);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("demandTokenOutModel", demandTokenOutModel);
        modelAndView.addObject("demandUserInfoOutModel", demandUserInfoOutModel);
        // TODO リダイレクト先設定

        return modelAndView;
    }
}
