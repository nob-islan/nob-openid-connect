package nob.example.rpappproject.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import nob.example.rpappproject.controller.AuthorizationController;
import nob.example.rpappproject.dto.DemandTokenInModel;
import nob.example.rpappproject.dto.DemandTokenOutModel;
import nob.example.rpappproject.dto.FetchTokenRequest;
import nob.example.rpappproject.dto.FetchTokenResponse;
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
    public FetchTokenResponse fetchToken(FetchTokenRequest fetchTokenRequest) {

        // トークンリクエスト
        DemandTokenInModel demandTokenInModel = new DemandTokenInModel();
        demandTokenInModel.setAuthorizationCode(fetchTokenRequest.getAuthorizationCode());
        demandTokenInModel.setCodeVerifier(fetchTokenRequest.getCodeVerifier());
        DemandTokenOutModel demandTokenOutModel = authorizationService.demandToken(demandTokenInModel);

        // 返却値を作成
        FetchTokenResponse fetchTokenResponse = new FetchTokenResponse();
        fetchTokenResponse.setAccessToken(demandTokenOutModel.getAccessToken());
        fetchTokenResponse.setIdToken(demandTokenOutModel.getIdToken());

        return fetchTokenResponse;
    }
}
