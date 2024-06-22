package nob.example.opappproject.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import nob.example.opappproject.controller.AuthorizationController;
import nob.example.opappproject.dto.CertificateInModel;
import nob.example.opappproject.dto.CertificateOutModel;
import nob.example.opappproject.dto.AuthorizeInModel;
import nob.example.opappproject.dto.AuthorizeOutModel;
import nob.example.opappproject.dto.AuthorizeRequest;
import nob.example.opappproject.dto.AuthorizeResponse;
import nob.example.opappproject.dto.CertificateRequest;
import nob.example.opappproject.dto.CertificateResponse;
import nob.example.opappproject.dto.IssueTokenInModel;
import nob.example.opappproject.dto.IssueTokenOutModel;
import nob.example.opappproject.dto.IssueTokenRequest;
import nob.example.opappproject.dto.IssueTokenResponse;
import nob.example.opappproject.service.AuthorizationService;

/**
 * 認証向けコントローラーの実装クラスです。
 * 
 * @author nob
 */
@RestController
public class AuthorizationControllerImpl implements AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public AuthorizeResponse authorize(AuthorizeRequest authorizeRequest) {

        // サービスを呼び出してリダイレクトURI検証
        AuthorizeInModel authorizeInModel = new AuthorizeInModel();
        authorizeInModel.setClientId(authorizeRequest.getClientId());
        authorizeInModel.setRedirectUri(authorizeRequest.getRedirectUri());
        AuthorizeOutModel authorizeOutModel = authorizationService.authorize(authorizeInModel);

        // 返却値の作成
        AuthorizeResponse authorizeResponse = new AuthorizeResponse();
        authorizeResponse.setRedirectUri(authorizeOutModel.getRedirectUri());

        return authorizeResponse;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public CertificateResponse certificate(CertificateRequest certificateRequest,
            HttpServletRequest httpServletRequest) {

        // inModel作成
        CertificateInModel certificateInModel = new CertificateInModel();
        certificateInModel.setUserId(certificateRequest.getUserId());
        certificateInModel.setPassword(certificateRequest.getPassword());
        certificateInModel.setRedirectUri(certificateRequest.getRedirectUri());
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            // CookieからcodeChallengeを取得してinModelにセット
            if (cookie.getName().equals("codeChallenge")) {
                certificateInModel.setCodeChallenge(cookie.getValue());
            }
        }

        // サービス呼び出し
        CertificateOutModel certificateOutModel = authorizationService.certificate(certificateInModel);

        // 返却値の作成
        CertificateResponse certificateResponse = new CertificateResponse();
        certificateResponse.setAuthorizationCode(certificateOutModel.getAuthorizationCode());
        certificateResponse.setRedirectUri(certificateOutModel.getRedirectUri());

        return certificateResponse;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public IssueTokenResponse issueToken(IssueTokenRequest issueTokenRequest) {

        // inModel作成
        IssueTokenInModel issueTokenInModel = new IssueTokenInModel();
        issueTokenInModel.setAuthorizationCode(issueTokenRequest.getAuthorizationCode());
        issueTokenInModel.setCodeVerifier(issueTokenRequest.getCodeVerifier());

        // サービス呼び出し
        IssueTokenOutModel issueTokenOutModel = authorizationService.issueToken(issueTokenInModel);

        // 返却値の作成
        IssueTokenResponse issueTokenResponse = new IssueTokenResponse();
        issueTokenResponse.setAccessToken(issueTokenOutModel.getAccessToken());
        issueTokenResponse.setIdToken(issueTokenOutModel.getIdToken());

        return issueTokenResponse;
    }
}
