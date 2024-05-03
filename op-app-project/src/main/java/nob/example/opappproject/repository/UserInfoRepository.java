package nob.example.opappproject.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import nob.example.opappproject.dto.UserCredentialSelectKey;
import nob.example.opappproject.entity.UserInfo;

/**
 * user_infoテーブル向けのrepositoryインターフェースです。
 * 
 * @author nob
 */
@Repository
public interface UserInfoRepository {

    /**
     * 入力されたクレデンシャルに合致するユーザ情報を取得します。
     * 
     * @param userCredentialSelectKey
     * @return クレデンシャルに合致するユーザの情報
     */
    List<UserInfo> selectUserCredential(UserCredentialSelectKey userCredentialSelectKey);
}
