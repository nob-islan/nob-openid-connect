package nob.example.rpappproject.service;

import org.springframework.stereotype.Service;

import nob.example.rpappproject.dto.DemandTokenInModel;
import nob.example.rpappproject.dto.DemandTokenOutModel;
import nob.example.rpappproject.exceptions.RpAuthException;

/**
 * 認証向けサービスのインターフェースです。
 * 
 * @author nob
 */
@Service
public interface AuthorizationService {

    /**
     * アクセストークンを要求します。
     * 
     * @param demandTokenInModel
     * @return アクセストークン
     * @throws RpAuthException
     */
    DemandTokenOutModel demandToken(DemandTokenInModel demandTokenInModel) throws RpAuthException;
}
