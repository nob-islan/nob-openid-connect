package nob.example.opappproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nob.example.opappproject.dto.RedirectUriSelectKey;
import nob.example.opappproject.entity.ClientInfo;

/**
 * client_infoテーブル向けのmapperクラスです。
 * 
 * @author nob
 */
@Mapper
public interface ClientInfoMapper {

    /**
     * 入力されたクライアントIDのリダイレクトURIを取得します。
     * 
     * @param redirectUriSelectKey
     * @return 指定されたクライアントIDのリダイレクトURI
     */
    List<ClientInfo> selectRedirectUri(RedirectUriSelectKey redirectUriSelectKey);
}
