package nob.example.opappproject.service.impl;

import org.springframework.stereotype.Service;

import nob.example.opappproject.dto.CertificationInModel;
import nob.example.opappproject.dto.CertificationOutModel;
import nob.example.opappproject.service.AuthorizationService;

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
