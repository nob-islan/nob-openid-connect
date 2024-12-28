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

    /**
     * OpenIDプロバイダの認可リクエスト検証API
     */
    private final String OP_VALIDATE_AUTHORIZATION_REQUEST_API = "http://localhost:8081/v1/api/auth/authorization";

    @Override
    public ModelAndView init() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageConstant.WELCOME);

        return modelAndView;
    }

    @Override
    public ModelAndView requestAuthorization() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + OP_VALIDATE_AUTHORIZATION_REQUEST_API);

        return modelAndView;
    }
}
