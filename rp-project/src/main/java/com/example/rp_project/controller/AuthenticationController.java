package com.example.rp_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.rp_project.constant.UrlConstant;
import com.example.rp_project.controller.reqres.FetchTokenRequest;

import jakarta.servlet.http.HttpServletResponse;

/**
 * 認証のコントローラインターフェースです。
 * 
 * @author nob
 */
@Controller
@RequestMapping(value = UrlConstant.LOGIN)
public interface AuthenticationController {

    /**
     * OIDC開始用の初期ページを表示します。
     * 
     * @return ログイン開始用の初期画面
     */
    @GetMapping(value = UrlConstant.WELCOME)
    ModelAndView init();

    /**
     * OpenIDプロバイダに認可要求を行います。
     * 
     * @return OpenIDプロバイダが提供するログイン画面
     */
    @GetMapping(value = UrlConstant.AUTHORIZATION)
    ModelAndView authorize();

    /**
     * OpenIDプロバイダにトークン発行要求を行い、受け取ったトークンを検証します。検証が正常に完了したらログイン完了画面に遷移します。
     * 
     * @return ログイン完了画面へのリダイレクト
     */
    @GetMapping(value = UrlConstant.TOKEN)
    ModelAndView fetchToken(HttpServletResponse httpServletResponse, FetchTokenRequest fetchTokenRequest);

    /**
     * ログイン完了ページを表示します。
     * 
     * @return ログイン完了画面
     */
    @GetMapping(value = UrlConstant.COMPLETE)
    ModelAndView complete();
}
