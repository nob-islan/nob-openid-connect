package nob.example.opappproject.repository;

import org.springframework.stereotype.Repository;

import nob.example.opappproject.entity.AuthorizationCodeInfo;

/**
 * authorization_code_infoテーブル向けのrepositoryインターフェースです。
 * 
 * @author nob
 */
@Repository
public interface AuthorizationCodeInfoRepository {

    /**
     * 認可コードおよび紐づくcodeChallengeを保存します。
     * 
     * @param authorizationCodeInfo
     */
    void insert(AuthorizationCodeInfo authorizationCodeInfo);
}
