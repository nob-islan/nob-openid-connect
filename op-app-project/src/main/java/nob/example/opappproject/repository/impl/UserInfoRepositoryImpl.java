package nob.example.opappproject.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nob.example.opappproject.dto.UserDataSelectKey;
import nob.example.opappproject.entity.UserInfo;
import nob.example.opappproject.mapper.UserInfoMapper;
import nob.example.opappproject.repository.UserInfoRepository;

/**
 * user_infoテーブル向けのrepository実装クラスです。
 * 
 * @author nob
 */
@Repository
public class UserInfoRepositoryImpl implements UserInfoRepository {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public List<UserInfo> selectUserData(UserDataSelectKey userDataSelectKey) {

        return userInfoMapper.selectUserData(userDataSelectKey);
    }
}
