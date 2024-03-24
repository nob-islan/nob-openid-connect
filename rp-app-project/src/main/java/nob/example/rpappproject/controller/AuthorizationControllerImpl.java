package nob.example.rpappproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import nob.example.rpappproject.constants.UrlConst;

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
        String redirectUrl = UrlConst.RP_WEB_ORIGIN + "/login";

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + redirectUrl);

        return modelAndView;
    }
}
