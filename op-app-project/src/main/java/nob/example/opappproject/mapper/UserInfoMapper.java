package nob.example.opappproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nob.example.opappproject.dto.UserCredentialSelectKey;
import nob.example.opappproject.dto.UserDataSelectKey;
import nob.example.opappproject.entity.UserInfo;

/**
 * user_infoテーブル向けのmapperクラスです。
 * 
 * @author nob
 */
@Mapper
public interface UserInfoMapper {

    /**
     * 画面に表示するユーザ情報を取得します。
     * 
     * @param userDataSelectKey
     * @return 検索条件に該当するユーザID, ユーザ名
     */
    List<UserInfo> selectUserData(UserDataSelectKey userDataSelectKey);

    /**
     * 入力されたクレデンシャルに合致するユーザ情報を取得します。
     * 
     * @param userCredentialSelectKey
     * @return クレデンシャルに合致するユーザの情報
     */
    List<UserInfo> selectUserCredential(UserCredentialSelectKey userCredentialSelectKey);
}
