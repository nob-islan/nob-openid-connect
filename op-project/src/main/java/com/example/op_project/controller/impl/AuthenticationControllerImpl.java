package com.example.op_project.controller.impl;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.op_project.controller.AuthenticationController;
import com.example.op_project.controller.form.ValidateAuthenticationRequestForm;

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
        // ユーザ名、パスワード入力用フォームをセット
        ValidateAuthenticationRequestForm validateAuthenticationRequestForm = new ValidateAuthenticationRequestForm();
        modelAndView.addObject("validateAuthenticationRequestForm", validateAuthenticationRequestForm);

        return modelAndView;
    }

    @Override
    public ModelAndView validateAuthenticationRequest(
            ValidateAuthenticationRequestForm validateAuthenticationRequestForm) {

        // TODO 認証リクエスト検証

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:http://localhost:8080/auth/token"); // TODO RPから指定されたリダイレクトURLをセット

        return modelAndView;
    }
}
