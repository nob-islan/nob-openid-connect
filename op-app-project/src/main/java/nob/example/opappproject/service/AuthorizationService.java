package nob.example.opappproject.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import nob.example.opappproject.dto.CertificationInModel;
import nob.example.opappproject.dto.CertificationOutModel;

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
    CertificationOutModel certificate(@RequestBody CertificationInModel certificationInModel);
}
