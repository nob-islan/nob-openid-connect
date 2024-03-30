package nob.example.opappproject.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import nob.example.opappproject.constants.UrlConst;
import nob.example.opappproject.controller.AuthorizationController;
import nob.example.opappproject.dto.CertificationInModel;
import nob.example.opappproject.dto.CertificationOutModel;
import nob.example.opappproject.dto.CertificationRequest;
import nob.example.opappproject.dto.CertificationResponse;
import nob.example.opappproject.dto.FetchUserInfoInModel;
import nob.example.opappproject.dto.FetchUserInfoOutModel;
import nob.example.opappproject.dto.FetchUserInfoRequest;
import nob.example.opappproject.dto.FetchUserInfoResponse;
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
    public ModelAndView authorize() {

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
    public CertificationResponse certificate(CertificationRequest certificationRequest) {

        // inModel作成
        CertificationInModel certificationInModel = new CertificationInModel();
        certificationInModel.setUserId(certificationRequest.getUserId());
        certificationInModel.setPassword(certificationRequest.getPassword());

        // サービス呼び出し
        CertificationOutModel certificationOutModel = authorizationService.certificate(certificationInModel);

        // outModel作成
        CertificationResponse certificationResponse = new CertificationResponse();
        certificationResponse.setIsCertificated(certificationOutModel.getIsCertificated());

        return certificationResponse;
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
