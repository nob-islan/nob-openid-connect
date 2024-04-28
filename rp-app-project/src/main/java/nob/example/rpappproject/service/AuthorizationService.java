package nob.example.rpappproject.service;

import org.springframework.stereotype.Service;

import nob.example.rpappproject.dto.RedirectAuthorizationOutModel;

/**
 * 認証向けサービスのインターフェースです。
 * 
 * @author nob
 */
@Service
public interface AuthorizationService {

    /**
     * 認可エンドポイントへのリダイレクトを行います。// TODO 名称変更
     * 
     * @return codeChallenge
     */
    RedirectAuthorizationOutModel redirectAuthorization();
}
