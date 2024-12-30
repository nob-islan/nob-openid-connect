package com.example.rp_project.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.example.rp_project.constant.PageConstant;
import com.example.rp_project.constant.UrlConstant;
import com.example.rp_project.controller.AuthenticationController;
import com.example.rp_project.controller.reqres.FetchTokenRequest;
import com.example.rp_project.op.constant.OpUrlConstant;
import com.example.rp_project.service.AuthenticationService;
import com.example.rp_project.service.inout.FetchTokenInModel;
import com.example.rp_project.service.inout.FetchTokenOutModel;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 認証のコントローラ実装です。
 * 
 * @author nob
 */
@Controller
public class AuthenticationControllerImpl implements AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

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
        modelAndView.setViewName("redirect:" + OpUrlConstant.AUTHORIZE_REQUEST);

        return modelAndView;
    }

    @Override
    public ModelAndView fetchToken(HttpServletResponse httpServletResponse, FetchTokenRequest fetchTokenRequest) {

        // サービス呼び出し
        FetchTokenInModel fetchTokenInModel = new FetchTokenInModel();
        fetchTokenInModel.setCode(fetchTokenRequest.getCode());
        FetchTokenOutModel fetchTokenOutModel = authenticationService.fetchToken(fetchTokenInModel);

        // トークンをcookieにセット
        Cookie accessTokenCookie = new Cookie("accessToken", fetchTokenOutModel.getAccessToken());
        accessTokenCookie.setSecure(true);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setPath("/");
        httpServletResponse.addCookie(accessTokenCookie);
        Cookie idTokenCookie = new Cookie("idToken", fetchTokenOutModel.getIdToken());
        idTokenCookie.setSecure(true);
        idTokenCookie.setHttpOnly(true);
        idTokenCookie.setPath("/");
        httpServletResponse.addCookie(idTokenCookie);

        // ログイン完了処理APIのビュー名をセット
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + UrlConstant.LOGIN + UrlConstant.COMPLETE);

        return modelAndView;
    }

    @Override
    public ModelAndView complete() {

        // ログイン完了画面のビュー名をセット
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageConstant.COMPLETE);

        return modelAndView;
    }
}
