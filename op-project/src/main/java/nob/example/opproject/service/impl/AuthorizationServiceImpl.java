package nob.example.opproject.service.impl;

import org.springframework.stereotype.Service;

import nob.example.opproject.dto.CertificationInModel;
import nob.example.opproject.dto.CertificationOutModel;
import nob.example.opproject.service.AuthorizationService;

/**
 * 認証向けサービスの実装クラスです。
 * 
 * @author nob
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public CertificationOutModel certificate(CertificationInModel certificationInModel) {

        // TODO 実装
        CertificationOutModel certificationOutModel = new CertificationOutModel();
        certificationOutModel.setIsCertificated(true);

        return certificationOutModel;
    }

}
