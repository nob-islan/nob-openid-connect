package nob.example.rpappproject.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import nob.example.rpappproject.controller.AuthorizationController;
import nob.example.rpappproject.dto.DemandTokenInModel;
import nob.example.rpappproject.dto.DemandTokenOutModel;
import nob.example.rpappproject.dto.DemandTokenRequest;
import nob.example.rpappproject.dto.DemandTokenResponse;
import nob.example.rpappproject.dto.FetchUserInfoInModel;
import nob.example.rpappproject.dto.FetchUserInfoOutModel;
import nob.example.rpappproject.dto.FetchUserInfoRequest;
import nob.example.rpappproject.dto.FetchUserInfoResponse;
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
    public DemandTokenResponse demandToken(DemandTokenRequest demandTokenRequest) {

        // inModel作成
        DemandTokenInModel demandTokenInModel = new DemandTokenInModel();
        demandTokenInModel.setAuthorizationCode(demandTokenRequest.getAuthorizationCode());
        demandTokenInModel.setCodeVerifier(demandTokenRequest.getCodeVerifier());

        // サービス呼び出し
        DemandTokenOutModel demandTokenOutModel = authorizationService.demandToken(demandTokenInModel);

        // outModel作成
        DemandTokenResponse demandTokenResponse = new DemandTokenResponse();
        demandTokenResponse.setAccessToken(demandTokenOutModel.getAccessToken());
        demandTokenResponse.setRefleshToken(demandTokenOutModel.getRefleshToken());
        demandTokenResponse.setIdToken(demandTokenOutModel.getIdToken());

        return demandTokenResponse;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public FetchUserInfoResponse fetchUserInfo(FetchUserInfoRequest fetchUserInfoRequest) {

        // inModel作成
        FetchUserInfoInModel fetchUserInfoInModel = new FetchUserInfoInModel();
        fetchUserInfoInModel.setUserId(fetchUserInfoRequest.getUserId());

        // サービス呼び出し
        FetchUserInfoOutModel fetchUserInfoOutModel = authorizationService.fetchUserInfo(fetchUserInfoInModel);

        // outModel作成
        FetchUserInfoResponse fetchUserInfoResponse = new FetchUserInfoResponse();
        fetchUserInfoResponse.setUserId(fetchUserInfoOutModel.getUserId());
        fetchUserInfoResponse.setUserName(fetchUserInfoOutModel.getUserName());

        return fetchUserInfoResponse;
    }
}
