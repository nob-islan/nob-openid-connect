package com.example.op_project.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.op_project.constant.UrlConstant;
import com.example.op_project.controller.reqres.AuthenticateRequest;
import com.example.op_project.controller.reqres.AuthorizeRequest;
import com.example.op_project.controller.reqres.FetchTokenRequest;
import com.example.op_project.controller.reqres.FetchTokenResponse;
import com.example.op_project.exception.OpSecurityException;

import jakarta.servlet.http.HttpServletRequest;
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
     * @param httpSession      HTTPセッション
     * @param authorizeRequest 認可リクエスト
     * @return ログインページ呼び出しAPIへのリダイレクト
     * @throws OpSecurityException
     */
    @GetMapping(value = "/authorization")
    ModelAndView authorize(HttpSession httpSession, AuthorizeRequest authorizeRequest) throws OpSecurityException;

    /**
     * 認証リクエストの検証を行います。
     * 
     * @param httpSession         HTTPセッション
     * @param authenticateRequest 認証リクエスト
     * @return RPによって指定されたリダイレクト先ページ
     * @throws OpSecurityException
     */
    @PostMapping(value = "/authentication")
    ModelAndView authenticate(Model model, HttpSession httpSession, HttpServletRequest httpServletRequest,
            AuthenticateRequest authenticateRequest)
            throws OpSecurityException;

    /**
     * トークンリクエストの検証を行います。
     * 
     * @param fetchTokenRequest トークンリクエスト
     * @return トークン
     * @throws OpSecurityException
     */
    @PostMapping(value = "/token")
    FetchTokenResponse fetchToken(@RequestBody FetchTokenRequest fetchTokenRequest) throws OpSecurityException;
}
