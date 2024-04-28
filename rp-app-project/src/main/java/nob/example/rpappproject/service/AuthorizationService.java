package nob.example.rpappproject.service;

import org.springframework.stereotype.Service;

import nob.example.rpappproject.dto.DemandTokenInModel;
import nob.example.rpappproject.dto.DemandTokenOutModel;
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

    /**
     * アクセストークンをリクエストします。
     * 
     * @param demandTokenInModel
     * @return アクセストークン
     */
    DemandTokenOutModel demandToken(DemandTokenInModel demandTokenInModel);
}
