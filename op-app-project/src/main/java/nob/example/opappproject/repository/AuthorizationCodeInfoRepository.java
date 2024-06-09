package nob.example.opappproject.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import nob.example.opappproject.dto.AuthorizationCodeInfoSelectKey;
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

    /**
     * 認可コードをキーとして、それに紐づく情報を取得します。
     * 
     * @return 認可コード情報
     */
    List<AuthorizationCodeInfo> selectAuthorizationCode(AuthorizationCodeInfoSelectKey authorizationCodeInfoSelectKey);

    /**
     * 指定された認可コードを論理削除します。
     * 
     * @param authorizationCodeInfo
     */
    void updateIsDeleted(AuthorizationCodeInfo authorizationCodeInfo);
}
