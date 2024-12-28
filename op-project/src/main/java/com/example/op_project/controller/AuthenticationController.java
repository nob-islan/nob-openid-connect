package com.example.op_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.op_project.constant.UrlConstant;

/**
 * OpenIDプロバイダとしての認証を行うコントローラインターフェースです。
 * 
 * @author nob
 */
@RestController
@RequestMapping(value = UrlConstant.AUTH)
public interface AuthenticationController {

    /**
     * 認可リクエストの検証を行います。
     * 
     * @return ログインページ
     */
    @GetMapping(value = "/authorization")
    ModelAndView validateAuthorizationRequest();

    /**
     * 認証リクエストの検証を行います。
     * 
     * @return RPによって指定されたリダイレクト先ページ
     */
    @GetMapping(value = "/authentication")
    ModelAndView validateAuthenticationRequest();
}
