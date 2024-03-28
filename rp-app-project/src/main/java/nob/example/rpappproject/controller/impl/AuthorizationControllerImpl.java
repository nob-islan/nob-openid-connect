package nob.example.rpappproject.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import nob.example.rpappproject.constants.UrlConst;
import nob.example.rpappproject.controller.AuthorizationController;

/**
 * 認証向けコントローラーの実装クラスです。
 * 
 * @author nob
 */
@Controller
public class AuthorizationControllerImpl implements AuthorizationController {

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
}
