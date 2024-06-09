package nob.example.opappproject.service;

import org.springframework.stereotype.Service;

import nob.example.opappproject.dto.AuthorizeInModel;
import nob.example.opappproject.dto.AuthorizeOutModel;
import nob.example.opappproject.dto.CertificateInModel;
import nob.example.opappproject.dto.CertificateOutModel;
import nob.example.opappproject.dto.IssueTokenInModel;
import nob.example.opappproject.dto.IssueTokenOutModel;

/**
 * 認証向けサービスのインターフェースです。
 * 
 * @author nob
 */
@Service
public interface AuthorizationService {

    /**
     * 認可処理を行います。
     * 
     * @param authorizeInModel
     * @return 認可の結果
     */
    AuthorizeOutModel authorize(AuthorizeInModel authorizeInModel);

    /**
     * ユーザID, パスワードによる認証を行います。
     * 
     * @param certificateInModel
     * @return 認証の結果
     */
    CertificateOutModel certificate(CertificateInModel certificateInModel);

    /**
     * アクセストークンを発行します。
     * 
     * @param issueTokenInModel
     * @return アクセストークン
     */
    IssueTokenOutModel issueToken(IssueTokenInModel issueTokenInModel);
}
