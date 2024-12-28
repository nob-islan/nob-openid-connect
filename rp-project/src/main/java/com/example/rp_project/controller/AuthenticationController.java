package com.example.rp_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.rp_project.constant.UrlConstant;

/**
 * 認証のコントローラインターフェースです。
 * 
 * @author nob
 */
@Controller
@RequestMapping(value = UrlConstant.AUTH)
public interface AuthenticationController {

    /**
     * OIDC開始用の初期ページを表示します。
     * 
     * @return
     */
    @GetMapping(value = "/login")
    ModelAndView init();

    /**
     * OpenIDプロバイダに認可要求を行います。
     * 
     * @return OpenIDプロバイダが提供するログイン画面
     */
    @GetMapping(value = "/authorization")
    ModelAndView requestAuthorization();
}
