package nob.example.opappproject.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import nob.example.opappproject.constants.UrlConst;
import nob.example.opappproject.controller.AuthorizationController;
import nob.example.opappproject.dto.CertificationInModel;
import nob.example.opappproject.dto.CertificationOutModel;
import nob.example.opappproject.dto.AuthorizeRequest;
import nob.example.opappproject.dto.CertificateRequest;
import nob.example.opappproject.dto.CertificationResponse;
import nob.example.opappproject.dto.FetchUserInfoInModel;
import nob.example.opappproject.dto.FetchUserInfoOutModel;
import nob.example.opappproject.dto.FetchUserInfoRequest;
import nob.example.opappproject.dto.FetchUserInfoResponse;
import nob.example.opappproject.dto.IssueTokenRequest;
import nob.example.opappproject.dto.IssueTokenResponse;
import nob.example.opappproject.service.AuthorizationService;

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
    public ModelAndView authorize(AuthorizeRequest authorizeRequest) {

        // TODO codeChallengeを保持

        // リダイレクトURL作成
        String redirectUrl = UrlConst.RP_WEB_ORIGIN + "/login";

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + redirectUrl);

        return modelAndView;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public CertificationResponse certificate(CertificateRequest certificateRequest) {

        // inModel作成
        CertificationInModel certificationInModel = new CertificationInModel();
        certificationInModel.setUserId(certificateRequest.getUserId());
        certificationInModel.setPassword(certificateRequest.getPassword());

        // サービス呼び出し
        CertificationOutModel certificationOutModel = authorizationService.certificate(certificationInModel);

        // outModel作成
        CertificationResponse certificationResponse = new CertificationResponse();
        certificationResponse.setAuthorizationCode(certificationOutModel.getAuthorizationCode());

        // TODO リダイレクト処理？
        return certificationResponse;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public IssueTokenResponse issueToken(IssueTokenRequest issueTokenRequest) {

        // TODO 認可コード、codeVerifier検証

        // TODO アクセストークン、リフレッシュトークン、IDトークン作成

        return new IssueTokenResponse();
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
