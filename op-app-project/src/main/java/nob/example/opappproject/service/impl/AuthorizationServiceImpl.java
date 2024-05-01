package nob.example.opappproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nob.example.opappproject.dto.CertificateInModel;
import nob.example.opappproject.dto.CertificateOutModel;
import nob.example.opappproject.dto.FetchUserInfoInModel;
import nob.example.opappproject.dto.FetchUserInfoOutModel;
import nob.example.opappproject.dto.UserCredentialSelectKey;
import nob.example.opappproject.dto.UserDataSelectKey;
import nob.example.opappproject.entity.UserInfo;
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
    private UserInfoRepository userInfoRepository;

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

        // TODO リダイレクトURI検証、OKであればoutModelにセットする。今は暫定でノールックでセットしている。

        // TODO 認可コード発行
        String authorizationCode = "";

        // 返却値の作成
        CertificateOutModel certificateOutModel = new CertificateOutModel();
        certificateOutModel.setAuthorizationCode(authorizationCode);
        certificateOutModel.setRedirectUri(certificateInModel.getRedirectUri());

        return certificateOutModel;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public FetchUserInfoOutModel fetchUserInfo(FetchUserInfoInModel fetchUserInfoInModel) {

        // DBアクセス用のリクエストモデルを作成
        UserDataSelectKey userDataSelectKey = new UserDataSelectKey();
        userDataSelectKey.setUserId(fetchUserInfoInModel.getUserId());

        // repository呼び出し // TODO 結果が0件だった場合の例外処理
        List<UserInfo> userInfoList = userInfoRepository.selectUserData(userDataSelectKey);

        // レスポンス作成
        FetchUserInfoOutModel fetchUserInfoOutModel = new FetchUserInfoOutModel();
        fetchUserInfoOutModel.setUserId(userInfoList.get(0).getUserId());
        fetchUserInfoOutModel.setUserName(userInfoList.get(0).getUserName());

        return fetchUserInfoOutModel;
    }
}
