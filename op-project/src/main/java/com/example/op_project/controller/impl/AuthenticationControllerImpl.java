package com.example.op_project.controller.impl;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.op_project.controller.AuthenticationController;

/**
 * OpenIDプロバイダとしての認証を行うコントローラ実装です。
 * 
 * @author nob
 */
@RestController
public class AuthenticationControllerImpl implements AuthenticationController {

    @Override
    public ModelAndView validateAuthorizationRequest() {

        // TODO 認可リクエスト検証

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");

        return modelAndView;
    }

    @Override
    public ModelAndView validateAuthenticationRequest() {

        // TODO 認証リクエスト検証

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(""); // TODO RPから指定されたリダイレクトURLをセット

        return modelAndView;
    }
}
