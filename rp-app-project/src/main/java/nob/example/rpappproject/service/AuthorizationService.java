package nob.example.rpappproject.service;

import org.springframework.stereotype.Service;

import nob.example.rpappproject.dto.DemandTokenInModel;
import nob.example.rpappproject.dto.DemandTokenOutModel;

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
     */
    DemandTokenOutModel demandToken(DemandTokenInModel demandTokenInModel);
}
