package com.example.rp_project.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.example.rp_project.constant.PageConstant;
import com.example.rp_project.controller.AuthenticationController;

/**
 * 認証のコントローラ実装です。
 * 
 * @author nob
 */
@Controller
public class AuthenticationControllerImpl implements AuthenticationController {

    @Override
    public ModelAndView init() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PageConstant.LOGIN);

        return modelAndView;
    }

    @Override
    public ModelAndView requestAuthorization() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:http://localhost:8081/v1/api/auth/authorization"); // TODO 定数クラス管理

        return modelAndView;
    }
}
