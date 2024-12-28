package com.example.rp_project.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.example.rp_project.constant.PageConstant;
import com.example.rp_project.controller.AuthenticationController;

/**
 * 認証のコントローラ実装です。
 * 
 * @author nob
 */
@Controller
public class AuthenticationControllerImpl implements AuthenticationController {

    @Override
    public ModelAndView init() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageConstant.WELCOME);

        return modelAndView;
    }

    @Override
    public ModelAndView requestAuthorization() {

        // OpenIDプロバイダの認可リクエスト検証API // TODO 管理方法検討
        final String OP_VALIDATE_AUTHORIZATION_REQUEST_API = "http://localhost:8081/v1/api/auth/authorization?clientId=first-client&redirectUri=http://localhost:8080/auth/token";

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + OP_VALIDATE_AUTHORIZATION_REQUEST_API);

        return modelAndView;
    }

    @Override
    public ModelAndView requestToken() {

        // TODO OpenIDプロバイダのトークン発行API呼び出し

        // TODO トークン検証

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageConstant.COMPLETE);

        return modelAndView;
    }
}
