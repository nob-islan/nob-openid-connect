package nob.example.opappproject.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import nob.example.opappproject.constants.UrlConst;
import nob.example.opappproject.controller.AuthorizationController;
import nob.example.opappproject.dto.CertificateInModel;
import nob.example.opappproject.dto.CertificateOutModel;
import nob.example.opappproject.dto.AuthorizeRequest;
import nob.example.opappproject.dto.CertificateRequest;
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

        // クエリパラメータを作成
        String queryParam = "?" + "redirectUri=" + authorizeRequest.getRedirectUri();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + UrlConst.OP_WEB_ORIGIN + UrlConst.LOGIN + queryParam);

        return modelAndView;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public ModelAndView certificate(CertificateRequest certificateRequest) {

        // TODO DBにcodeChallengeを保持

        // inModel作成
        CertificateInModel certificateInModel = new CertificateInModel();
        certificateInModel.setUserId(certificateRequest.getUserId());
        certificateInModel.setPassword(certificateRequest.getPassword());
        certificateInModel.setRedirectUri(certificateRequest.getRedirectUri());

        // サービス呼び出し
        CertificateOutModel certificateOutModel = authorizationService.certificate(certificateInModel);

        // クエリパラメータを作成
        String queryParam = "?" + "authorizationCode=" + certificateOutModel.getAuthorizationCode();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + certificateOutModel.getRedirectUri() + queryParam);

        return modelAndView;
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
