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
     */
    @GetMapping(value = "/login")
    ModelAndView init();
}