package nob.example.rpappproject.service;

import org.springframework.stereotype.Service;

import nob.example.rpappproject.dto.DemandTokenInModel;
import nob.example.rpappproject.dto.DemandTokenOutModel;
import nob.example.rpappproject.dto.DemandUserInfoInModel;
import nob.example.rpappproject.dto.DemandUserInfoOutModel;
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
     * アクセストークンを要求します。
     * 
     * @param demandTokenInModel
     * @return アクセストークン
     */
    DemandTokenOutModel demandToken(DemandTokenInModel demandTokenInModel);

    /**
     * ユーザ情報を要求します。
     * 
     * @param demandUserInfoInModel
     * @return ユーザ情報
     */
    DemandUserInfoOutModel demandUserInfo(DemandUserInfoInModel demandUserInfoInModel);
}
