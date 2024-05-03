package nob.example.opappproject.service;

import org.springframework.stereotype.Service;

import nob.example.opappproject.dto.CertificateInModel;
import nob.example.opappproject.dto.CertificateOutModel;

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
     * @param certificateInModel
     * @return 認証の結果
     */
    CertificateOutModel certificate(CertificateInModel certificateInModel);
}
