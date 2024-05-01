package nob.example.rpappproject.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import nob.example.rpappproject.controller.AuthorizationController;
import nob.example.rpappproject.dto.CalcCodeChallengeOutModel;
import nob.example.rpappproject.dto.DemandTokenInModel;
import nob.example.rpappproject.dto.DemandTokenOutModel;
import nob.example.rpappproject.dto.FetchTokenRequest;
import nob.example.rpappproject.dto.RedirectAuthorizationRequest;
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
    public ModelAndView redirectAuthorization(RedirectAuthorizationRequest redirectAuthorizationRequest,
            HttpServletResponse httpServletResponse) {

        // サービスを呼び出してcodeChallengeを計算
        CalcCodeChallengeOutModel calcCodeChallengeOutModel = authorizationService.redirectAuthorization();

        // codeVerifierをCookieに保持
        Cookie cookie = new Cookie("codeVerifier", calcCodeChallengeOutModel.getCodeVerifier());
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setMaxAge(600);
        httpServletResponse.addCookie(cookie);

        // クエリパラメータを作成
        String queryParam = "?" + "codeChallenge=" + calcCodeChallengeOutModel.getCodeChallenge()
                + "&codeChallengeMethod=" + calcCodeChallengeOutModel.getCodeChallengeMethod()
                + "&redirectUri=" + redirectAuthorizationRequest.getRedirectUri();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(HttpStatus.FOUND);
        modelAndView.setViewName(
                "redirect:" + UrlConst.OP_APP_ORIGIN + UrlConst.BASE_URL + UrlConst.AUTHORIZATION + queryParam);

        return modelAndView;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public ModelAndView fetchToken(FetchTokenRequest fetchTokenRequest, RedirectAttributes redirectAttributes) {

        // TODO codeVerifierをCookieから取り出す

        // トークンリクエスト
        DemandTokenInModel demandTokenInModel = new DemandTokenInModel();
        demandTokenInModel.setAuthorizationCode(fetchTokenRequest.getAuthorizationCode());
        demandTokenInModel.setCodeVerifier(fetchTokenRequest.getCodeVerifier());
        DemandTokenOutModel demandTokenOutModel = authorizationService.demandToken(demandTokenInModel);

        // TODO IDトークン検証

        // // ユーザ情報リクエスト
        // DemandUserInfoInModel demandUserInfoInModel = new DemandUserInfoInModel();
        // demandUserInfoInModel.setUserId(fetchTokenRequest.getUserId());
        // DemandUserInfoOutModel demandUserInfoOutModel =
        // authorizationService.demandUserInfo(demandUserInfoInModel);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(HttpStatus.FOUND);
        redirectAttributes.addFlashAttribute("demandTokenOutModel", demandTokenOutModel);
        // modelAndView.addObject("demandUserInfoOutModel", demandUserInfoOutModel);
        modelAndView.setViewName("redirect:" + UrlConst.RP_WEB_ORIGIN + UrlConst.TOP);

        return modelAndView; // TODO リダイレクト方式はこれでOK? modelの返し方、画面側での参照の仕方も検討
    }
}
