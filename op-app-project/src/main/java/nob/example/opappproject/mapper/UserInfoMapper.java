package nob.example.opappproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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
     * 検索条件に従ってユーザ情報を取得します。
     * 
     * @param userDataSelectKey
     * @return 検索条件に該当するユーザID, ユーザ名
     */
    List<UserInfo> selectUserData(UserDataSelectKey userDataSelectKey);
}
