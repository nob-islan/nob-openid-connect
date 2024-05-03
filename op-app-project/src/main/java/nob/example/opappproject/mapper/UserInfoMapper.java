package nob.example.opappproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nob.example.opappproject.dto.UserCredentialSelectKey;
import nob.example.opappproject.entity.UserInfo;

/**
 * user_infoテーブル向けのmapperクラスです。
 * 
 * @author nob
 */
@Mapper
public interface UserInfoMapper {

    /**
     * 入力されたクレデンシャルに合致するユーザ情報を取得します。
     * 
     * @param userCredentialSelectKey
     * @return クレデンシャルに合致するユーザの情報
     */
    List<UserInfo> selectUserCredential(UserCredentialSelectKey userCredentialSelectKey);
}
