package nob.example.opappproject.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nob.example.opappproject.dao.UserInfoDao;
import nob.example.opappproject.dto.UserInfoSearchConditionDto;
import nob.example.opappproject.entity.UserInfo;
import nob.example.opappproject.mapper.UserInfoMapper;

/**
 * user_infoテーブル向けのdao実装クラスです。
 * 
 * @author nob
 */
@Component
public class UserInfoDaoImpl implements UserInfoDao {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public List<UserInfo> selectByCondition(UserInfoSearchConditionDto userInfoSearchConditionDto) {

        return userInfoMapper.selectByCondition(userInfoSearchConditionDto);
    }
}
