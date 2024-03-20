package nob.example.opproject.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nob.example.opproject.constants.UrlConst;
import nob.example.opproject.dto.CertificationInModel;
import nob.example.opproject.dto.CertificationOutModel;

/**
 * 認証向けサービスのインターフェースです。
 * 
 * @author nob
 */
@RestController
@RequestMapping(value = UrlConst.BASE_URL)
public interface AuthorizationService {

    /**
     * ユーザID, パスワードによる認証を行います。
     * 
     * @param certificationInModel
     * @return 認証の結果
     */
    @PostMapping(value = UrlConst.CERTIFICATION)
    CertificationOutModel certificate(CertificationInModel certificationInModel);
}
