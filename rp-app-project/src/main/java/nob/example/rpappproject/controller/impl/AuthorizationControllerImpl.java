package nob.example.rpappproject.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import nob.example.rpappproject.constants.UrlConst;
import nob.example.rpappproject.controller.AuthorizationController;
import nob.example.rpappproject.dto.CalcCodeChallengeOutModel;
import nob.example.rpappproject.dto.DemandTokenInModel;
import nob.example.rpappproject.dto.DemandTokenOutModel;
import nob.example.rpappproject.dto.FetchTokenRequest;
import nob.example.rpappproject.dto.FetchTokenResponse;
import nob.example.rpappproject.dto.RedirectAuthorizationRequest;
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
                "redirect:" + UrlConst.OP_WEB_ORIGIN + UrlConst.REDIRECT_AUTHORIZE + queryParam);

        return modelAndView;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public FetchTokenResponse fetchToken(FetchTokenRequest fetchTokenRequest) {

        // トークンリクエスト
        DemandTokenInModel demandTokenInModel = new DemandTokenInModel();
        demandTokenInModel.setAuthorizationCode(fetchTokenRequest.getAuthorizationCode());
        demandTokenInModel.setCodeVerifier(fetchTokenRequest.getCodeVerifier());
        DemandTokenOutModel demandTokenOutModel = authorizationService.demandToken(demandTokenInModel);

        // TODO IDトークン検証

        // 返却値を作成
        FetchTokenResponse fetchTokenResponse = new FetchTokenResponse();
        fetchTokenResponse.setAccessToken(demandTokenOutModel.getAccessToken());
        fetchTokenResponse.setRefleshToken(demandTokenOutModel.getRefleshToken());
        fetchTokenResponse.setIdToken(demandTokenOutModel.getIdToken());

        return fetchTokenResponse;
    }
}
