package nob.example.opproject.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import nob.example.opproject.dto.UserInfoSearchConditionDto;
import nob.example.opproject.entity.UserInfo;

/**
 * user_infoテーブル向けのdaoインターフェースです。
 * 
 * @author nob
 */
@Component
public interface UserInfoDao {

    /**
     * 検索条件に従ってユーザ情報を取得します。
     * 
     * @param userInfoSearchConditionDto
     * @return ユーザ情報検索結果
     */
    List<UserInfo> selectByCondition(UserInfoSearchConditionDto userInfoSearchConditionDto);
}
