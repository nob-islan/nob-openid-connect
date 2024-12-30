package com.example.rp_project.service;

import org.springframework.stereotype.Service;

import com.example.rp_project.exception.RpException;
import com.example.rp_project.service.inout.FetchTokenInModel;
import com.example.rp_project.service.inout.FetchTokenOutModel;

/**
 * 認証のサービスインターフェースです。
 * 
 * @author nob
 */
@Service
public interface AuthenticationService {

    /**
     * OpenIDプロバイダにトークン発行要求を行い、受け取ったトークンを検証します。
     * 
     * @param fetchTokenInModel トークン発行要求
     * @return トークン
     */
    FetchTokenOutModel fetchToken(FetchTokenInModel fetchTokenInModel) throws RpException;
}
