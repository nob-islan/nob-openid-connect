package nob.example.opappproject.service;

import org.springframework.stereotype.Service;

import nob.example.opappproject.dto.AuthorizeInModel;
import nob.example.opappproject.dto.AuthorizeOutModel;
import nob.example.opappproject.dto.CertificateInModel;
import nob.example.opappproject.dto.CertificateOutModel;
import nob.example.opappproject.dto.IssueTokenInModel;
import nob.example.opappproject.dto.IssueTokenOutModel;
import nob.example.opappproject.exceptions.OpAuthException;

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
     * @throws OpAuthException
     */
    AuthorizeOutModel authorize(AuthorizeInModel authorizeInModel) throws OpAuthException;

    /**
     * ユーザID, パスワードによる認証を行います。
     * 
     * @param certificateInModel
     * @return 認証の結果
     * @throws OpAuthException
     */
    CertificateOutModel certificate(CertificateInModel certificateInModel) throws OpAuthException;

    /**
     * アクセストークンを発行します。
     * 
     * @param issueTokenInModel
     * @return アクセストークン
     * @throws OpAuthException
     */
    IssueTokenOutModel issueToken(IssueTokenInModel issueTokenInModel) throws OpAuthException;
}
