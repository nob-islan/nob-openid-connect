package nob.example.opappproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nob.example.opappproject.dto.AuthorizeInModel;
import nob.example.opappproject.dto.AuthorizeOutModel;
import nob.example.opappproject.dto.CertificateInModel;
import nob.example.opappproject.dto.CertificateOutModel;
import nob.example.opappproject.dto.RedirectUriSelectKey;
import nob.example.opappproject.dto.UserCredentialSelectKey;
import nob.example.opappproject.entity.ClientInfo;
import nob.example.opappproject.entity.UserInfo;
import nob.example.opappproject.repository.ClientInfoRepository;
import nob.example.opappproject.repository.UserInfoRepository;
import nob.example.opappproject.service.AuthorizationService;

/**
 * 認証向けサービスの実装クラスです。
 * 
 * @author nob
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private ClientInfoRepository clientInfoRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public AuthorizeOutModel authorize(AuthorizeInModel authorizeInModel) {

        // DBからリダイレクトURI取得
        RedirectUriSelectKey redirectUriSelectKey = new RedirectUriSelectKey();
        redirectUriSelectKey.setClientId(authorizeInModel.getClientId());
        List<ClientInfo> clientInfoList = clientInfoRepository.selectRedirectUri(redirectUriSelectKey);
        // DBから取得したリダイレクトURLとinModelのリダイレクトURLとを比較
        if (!clientInfoList.get(0).getRedirectUri().equals(authorizeInModel.getRedirectUri())) {
            System.out.println("認証失敗"); // TODO 例外作成
        }

        // 検証OKであればリダイレクトURIをそのまま返却
        AuthorizeOutModel authorizeOutModel = new AuthorizeOutModel();
        authorizeOutModel.setRedirectUri(authorizeInModel.getRedirectUri());

        return authorizeOutModel;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public CertificateOutModel certificate(CertificateInModel certificateInModel) {

        // クレデンシャル検証
        UserCredentialSelectKey userCredentialSelectKey = new UserCredentialSelectKey();
        userCredentialSelectKey.setUserId(certificateInModel.getUserId());
        userCredentialSelectKey.setPassword(certificateInModel.getPassword());
        List<UserInfo> userInfoList = userInfoRepository.selectUserCredential(userCredentialSelectKey);
        // ヒットした件数が1件でなければ認証失敗とする
        if (userInfoList.size() != 1) {
            System.out.println("認証失敗"); // TODO 例外作成
        }

        // TODO 認可コード発行
        String authorizationCode = "testAuthorizationCode";

        // 返却値の作成
        CertificateOutModel certificateOutModel = new CertificateOutModel();
        certificateOutModel.setAuthorizationCode(authorizationCode);
        certificateOutModel.setRedirectUri(certificateInModel.getRedirectUri());

        return certificateOutModel;
    }
}
