package nob.example.opappproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nob.example.opappproject.dto.AuthorizationCodeInfoSelectKey;
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

    /**
     * 認可コードをキーとして、それに紐づく情報を取得します。
     * 
     * @return 認可コード情報
     */
    List<AuthorizationCodeInfo> selectAuthorizationCode(AuthorizationCodeInfoSelectKey authorizationCodeInfoSelectKey);
}
