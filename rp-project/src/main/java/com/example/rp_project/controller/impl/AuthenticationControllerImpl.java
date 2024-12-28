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
}
