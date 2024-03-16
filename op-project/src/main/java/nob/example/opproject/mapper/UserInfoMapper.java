package nob.example.opproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nob.example.opproject.dto.UserInfoSearchConditionDto;
import nob.example.opproject.entity.UserInfo;

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
     * @param userInfoSearchConditionDto
     * @return ユーザ情報検索結果
     */
    List<UserInfo> selectByCondition(UserInfoSearchConditionDto userInfoSearchConditionDto);
}
