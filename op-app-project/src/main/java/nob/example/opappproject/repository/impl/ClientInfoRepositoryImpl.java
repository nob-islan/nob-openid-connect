package nob.example.opappproject.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nob.example.opappproject.dto.RedirectUriSelectKey;
import nob.example.opappproject.entity.ClientInfo;
import nob.example.opappproject.mapper.ClientInfoMapper;
import nob.example.opappproject.repository.ClientInfoRepository;

/**
 * client_infoテーブル向けのrepository実装クラスです。
 * 
 * @author nob
 */
@Repository
public class ClientInfoRepositoryImpl implements ClientInfoRepository {

    @Autowired
    private ClientInfoMapper clientInfoMapper;

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public List<ClientInfo> selectRedirectUri(RedirectUriSelectKey redirectUriSelectKey) {

        return clientInfoMapper.selectRedirectUri(redirectUriSelectKey);
    }
}
