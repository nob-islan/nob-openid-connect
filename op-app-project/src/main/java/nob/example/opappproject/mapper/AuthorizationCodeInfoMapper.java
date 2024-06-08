package nob.example.opappproject.mapper;

import org.apache.ibatis.annotations.Mapper;

import nob.example.opappproject.entity.AuthorizationCodeInfo;

/**
 * authorization_code_infoテーブル向けのmapperクラスです。
 * 
 * @author nob
 */
@Mapper
public interface AuthorizationCodeInfoMapper {

    /**
     * 認可コードおよび紐づくcodeChallengeを保存します。
     * 
     * @param authorizationCodeInfo
     */
    void insert(AuthorizationCodeInfo authorizationCodeInfo);
}
