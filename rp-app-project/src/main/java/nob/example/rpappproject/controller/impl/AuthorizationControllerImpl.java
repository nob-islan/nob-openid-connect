package nob.example.rpappproject.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import nob.example.rpappproject.constants.UrlConst;
import nob.example.rpappproject.controller.AuthorizationController;
import nob.example.rpappproject.dto.FetchUserInfoInModel;
import nob.example.rpappproject.dto.FetchUserInfoOutModel;
import nob.example.rpappproject.service.AuthorizationService;

/**
 * 認証向けコントローラーの実装クラスです。
 * 
 * @author nob
 */
@Controller
public class AuthorizationControllerImpl implements AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public ModelAndView redirectLogin() {

        // リダイレクトURL作成
        String redirectUrl = UrlConst.OP_APP_ORIGIN + "/api/op/authorization";

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + redirectUrl);

        return modelAndView;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public FetchUserInfoOutModel fetchUserInfo(FetchUserInfoInModel fetchUserInfoInModel) {

        // サービス呼び出し
        FetchUserInfoOutModel fetchUserInfoOutModel = authorizationService.fetchUserInfo(fetchUserInfoInModel);

        return fetchUserInfoOutModel;
    }
}
