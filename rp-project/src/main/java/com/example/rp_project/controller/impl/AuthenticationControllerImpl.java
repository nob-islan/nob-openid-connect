package com.example.rp_project.controller.impl;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
        modelAndView.setViewName("redirect:" + OpConstant.AUTHORIZE_REQUEST);

        return modelAndView;
    }

    @Override
    public ModelAndView fetchToken(FetchTokenRequest fetchTokenRequest) {

        // OpenIDプロバイダのトークン発行API呼び出し // TODO サービスでやれ
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            // リクエストの作成
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(OpConstant.TOKEN_API))
                    .POST(HttpRequest.BodyPublishers.ofString(
                            "{ \"name\" : \"Qiita Neko\" }"))
                    .build();

            // リクエストの送信とレスポンスの取得
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace(); // TODO エラーハンドリング
        }

        // TODO トークン検証

        // ログイン完了ページのビュー名をセット
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageConstant.COMPLETE);

        return modelAndView;
    }
}
