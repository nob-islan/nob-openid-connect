package nob.example.opappproject.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import nob.example.opappproject.dto.RedirectUriSelectKey;
import nob.example.opappproject.entity.ClientInfo;

/**
 * client_infoテーブル向けのrepositoryインターフェースです。
 * 
 * @author nob
 */
@Repository
public interface ClientInfoRepository {

    /**
     * 入力されたクライアントIDのリダイレクトURIを取得します。
     * 
     * @param redirectUriSelectKey
     * @return 指定されたクライアントIDのリダイレクトURI
     */
    List<ClientInfo> selectRedirectUri(RedirectUriSelectKey redirectUriSelectKey);
}
