package com.example.op_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.op_project.constant.UrlConstant;
import com.example.op_project.controller.reqres.AuthenticateRequest;
import com.example.op_project.controller.reqres.AuthorizeRequest;
import com.example.op_project.exception.OpException;

import jakarta.servlet.http.HttpSession;

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
    ModelAndView authorize(HttpSession httpSession, AuthorizeRequest authorizeRequest) throws OpException;

    /**
     * 認証リクエストの検証を行います。
     * 
     * @return RPによって指定されたリダイレクト先ページ
     */
    @PostMapping(value = "/authentication")
    ModelAndView authenticate(HttpSession httpSession, AuthenticateRequest authenticateRequest) throws OpException;
}
