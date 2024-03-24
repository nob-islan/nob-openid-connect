package nob.example.opappproject.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import nob.example.opappproject.constants.UrlConst;
import nob.example.opappproject.controller.AuthorizationController;
import nob.example.opappproject.dto.CertificationInModel;
import nob.example.opappproject.dto.CertificationOutModel;
import nob.example.opappproject.dto.FetchUserInfoInModel;
import nob.example.opappproject.dto.FetchUserInfoOutModel;
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
    public CertificationOutModel certificate(CertificationInModel certificationInModel) {

        // サービス呼び出し
        return authorizationService.certificate(certificationInModel);
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public FetchUserInfoOutModel fetchUserInfo(FetchUserInfoInModel fetchUserInfoInModel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fetchUserInfo'");
    }
}
