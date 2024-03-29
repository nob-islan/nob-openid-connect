package nob.example.rpappproject.service;

import org.springframework.stereotype.Service;

import nob.example.rpappproject.dto.FetchUserInfoInModel;
import nob.example.rpappproject.dto.FetchUserInfoOutModel;

/**
 * 認証向けサービスのインターフェースです。
 * 
 * @author nob
 */
@Service
public interface AuthorizationService {

    /**
     * ユーザ情報を取得します。
     * 
     * @param fetchUserInfoInModel
     * @return OP上のユーザ情報
     */
    FetchUserInfoOutModel fetchUserInfo(FetchUserInfoInModel fetchUserInfoInModel);
}
