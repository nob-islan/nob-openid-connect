package com.example.rp_project.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.example.rp_project.constant.OpConstant;
import com.example.rp_project.constant.PageConstant;
import com.example.rp_project.controller.AuthenticationController;
import com.example.rp_project.controller.reqres.FetchTokenRequest;

/**
 * 認証のコントローラ実装です。
 * 
 * @author nob
 */
@Controller
public class AuthenticationControllerImpl implements AuthenticationController {

    @Override
    public ModelAndView init() {

        // ログイン開始ページのビュー名をセット
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageConstant.WELCOME);

        return modelAndView;
    }

    @Override
    public ModelAndView authorize() {

        // OpenIDプロバイダの認可リクエスト先のビュー名をセット
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + OpConstant.AUTHORIZE_API);

        return modelAndView;
    }

    @Override
    public ModelAndView fetchToken(FetchTokenRequest fetchTokenRequest) {

        // TODO OpenIDプロバイダのトークン発行API呼び出し

        // TODO トークン検証

        // ログイン完了ページのビュー名をセット
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageConstant.COMPLETE);

        return modelAndView;
    }
}
