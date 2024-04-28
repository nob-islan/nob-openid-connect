package nob.example.opappproject.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import nob.example.opappproject.dto.UserDataSelectKey;
import nob.example.opappproject.entity.UserInfo;

/**
 * user_infoテーブル向けのrepositoryインターフェースです。
 * 
 * @author nob
 */
@Repository
public interface UserInfoRepository {

    /**
     * 検索条件に従ってユーザ情報を取得します。
     * 
     * @param userDataSelectKey
     * @return 検索条件に該当するユーザID, ユーザ名
     */
    List<UserInfo> selectUserData(UserDataSelectKey userDataSelectKey);
}
