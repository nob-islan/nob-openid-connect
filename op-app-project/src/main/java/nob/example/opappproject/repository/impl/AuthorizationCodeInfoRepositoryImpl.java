package nob.example.opappproject.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nob.example.opappproject.dto.AuthorizationCodeInfoSelectKey;
import nob.example.opappproject.entity.AuthorizationCodeInfo;
import nob.example.opappproject.mapper.AuthorizationCodeInfoMapper;
import nob.example.opappproject.repository.AuthorizationCodeInfoRepository;

/**
 * authorization_code_infoテーブル向けのrepository実装クラスです。
 * 
 * @author nob
 */
@Repository
public class AuthorizationCodeInfoRepositoryImpl implements AuthorizationCodeInfoRepository {

    @Autowired
    private AuthorizationCodeInfoMapper authorizationCodeInfoMapper;

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public void insert(AuthorizationCodeInfo authorizationCodeInfo) {

        authorizationCodeInfoMapper.insert(authorizationCodeInfo);
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public List<AuthorizationCodeInfo> selectAuthorizationCode(
            AuthorizationCodeInfoSelectKey authorizationCodeInfoSelectKey) {

        return authorizationCodeInfoMapper.selectAuthorizationCode(authorizationCodeInfoSelectKey);
    }
}
