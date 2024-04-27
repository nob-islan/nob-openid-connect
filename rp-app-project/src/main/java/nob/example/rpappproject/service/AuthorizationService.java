package nob.example.rpappproject.service;

import org.springframework.stereotype.Service;

import nob.example.rpappproject.dto.DemandTokenInModel;
import nob.example.rpappproject.dto.DemandTokenOutModel;
import nob.example.rpappproject.dto.FetchUserInfoInModel;
import nob.example.rpappproject.dto.FetchUserInfoOutModel;
import nob.example.rpappproject.dto.RedirectAuthorizationOutModel;

/**
 * 認証向けサービスのインターフェースです。
 * 
 * @author nob
 */
@Service
public interface AuthorizationService {

    /**
     * 認可エンドポイントへのリダイレクトを行います。
     * 
     * @return codeChallenge
     */
    RedirectAuthorizationOutModel redirectAuthorization();

    /**
     * アクセストークンおよびIDトークンを要求します。
     * 
     * @param demandTokenInModel
     * @return OPから発行されたトークン
     */
    DemandTokenOutModel demandToken(DemandTokenInModel demandTokenInModel);

    /**
     * ユーザ情報を取得します。
     * 
     * @param fetchUserInfoInModel
     * @return OP上のユーザ情報
     */
    FetchUserInfoOutModel fetchUserInfo(FetchUserInfoInModel fetchUserInfoInModel);
}
