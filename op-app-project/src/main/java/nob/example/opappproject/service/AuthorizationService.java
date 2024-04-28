package nob.example.opappproject.service;

import org.springframework.stereotype.Service;

import nob.example.opappproject.dto.CertificateInModel;
import nob.example.opappproject.dto.CertificateOutModel;
import nob.example.opappproject.dto.FetchUserInfoInModel;
import nob.example.opappproject.dto.FetchUserInfoOutModel;

/**
 * 認証向けサービスのインターフェースです。
 * 
 * @author nob
 */
@Service
public interface AuthorizationService {

    /**
     * ユーザID, パスワードによる認証を行います。
     * 
     * @param certificationInModel
     * @return 認証の結果
     */
    CertificateOutModel certificate(CertificateInModel certificateInModel);

    /**
     * UserInfoを取得します。
     * 
     * @param fetchUserInfoInModel
     * @return UserInfo
     */
    FetchUserInfoOutModel fetchUserInfo(FetchUserInfoInModel fetchUserInfoInModel);
}
