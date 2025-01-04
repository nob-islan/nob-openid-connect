package com.example.op_project.controller.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.op_project.constant.PageConstant;
import com.example.op_project.controller.AuthenticationController;
import com.example.op_project.controller.reqres.AuthenticateRequest;
import com.example.op_project.controller.reqres.AuthorizeRequest;
import com.example.op_project.controller.reqres.FetchTokenRequest;
import com.example.op_project.controller.reqres.FetchTokenResponse;
import com.example.op_project.exception.OpBusinessException;
import com.example.op_project.exception.OpSecurityException;
import com.example.op_project.service.AuthenticationService;
import com.example.op_project.service.inout.AuthenticateInModel;
import com.example.op_project.service.inout.AuthenticateOutModel;
import com.example.op_project.service.inout.AuthorizeInModel;
import com.example.op_project.service.inout.FetchTokenInModel;
import com.example.op_project.service.inout.FetchTokenOutModel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * OpenIDプロバイダとしての認証を行うコントローラ実装です。
 * 
 * @author nob
 */
@RestController
public class AuthenticationControllerImpl implements AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public ModelAndView authorize(HttpSession httpSession, AuthorizeRequest authorizeRequest)
            throws OpSecurityException {

        // 認可リクエストを検証 不正であれば例外を投げる
        AuthorizeInModel authorizeInModel = new AuthorizeInModel();
        BeanUtils.copyProperties(authorizeRequest, authorizeInModel);
        authenticationService.authorize(authorizeInModel);

        // httpSessionにリダイレクトURI、クライアントIDをセット
        httpSession.setAttribute("redirectUri", authorizeInModel.getRedirectUri());
        httpSession.setAttribute("clientId", authorizeInModel.getClientId());

        // ログイン画面のビュー名およびモデルをセット
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageConstant.LOGIN);
        modelAndView.addObject("authenticateRequest", new AuthenticateRequest());

        return modelAndView;
    }

    @Override
    public ModelAndView authenticate(Model model, HttpSession httpSession, HttpServletRequest httpServletRequest,
            AuthenticateRequest authenticateRequest) {

        // 認証リクエスト検証用inModel作成
        AuthenticateInModel authenticateInModel = new AuthenticateInModel();
        BeanUtils.copyProperties(authenticateRequest, authenticateInModel);
        // 認証リクエスト検証
        AuthenticateOutModel authenticateOutModel = new AuthenticateOutModel();
        try {
            authenticateOutModel = authenticationService.authenticate(authenticateInModel);
        } catch (OpBusinessException e) {
            // エラーメッセージをセットして自画面遷移
            model.addAttribute("errorMessage", e.getMessage());
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName(PageConstant.LOGIN);
            return modelAndView;
        }

        // httpSessionからリダイレクトURIを取得し、認可コードをクエリパラメータとして付与
        String redirectUri = (String) httpSession.getAttribute("redirectUri");
        redirectUri = redirectUri + "?code=" + authenticateOutModel.getCode();

        // RPによって指定されたリダイレクトURIをビュー名にセット
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + redirectUri);

        return modelAndView;
    }

    @Override
    public FetchTokenResponse fetchToken(FetchTokenRequest fetchTokenRequest)
            throws OpSecurityException {

        // サービス呼び出しモデル作成
        FetchTokenInModel fetchTokenInModel = new FetchTokenInModel();
        BeanUtils.copyProperties(fetchTokenRequest, fetchTokenInModel);

        // トークンリクエスト検証 不正であれば例外を投げる
        FetchTokenOutModel fetchTokenOutModel = authenticationService.fetchToken(fetchTokenInModel);

        // 返却値を作成
        FetchTokenResponse fetchTokenResponse = new FetchTokenResponse();
        BeanUtils.copyProperties(fetchTokenOutModel, fetchTokenResponse);

        return fetchTokenResponse;
    }
}
