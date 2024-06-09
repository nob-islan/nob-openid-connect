package nob.example.opappproject.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.HexFormat;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nob.example.opappproject.dto.AuthorizationCodeInfoSelectKey;
import nob.example.opappproject.dto.AuthorizeInModel;
import nob.example.opappproject.dto.AuthorizeOutModel;
import nob.example.opappproject.dto.CertificateInModel;
import nob.example.opappproject.dto.CertificateOutModel;
import nob.example.opappproject.dto.IssueTokenInModel;
import nob.example.opappproject.dto.IssueTokenOutModel;
import nob.example.opappproject.dto.RedirectUriSelectKey;
import nob.example.opappproject.dto.UserCredentialSelectKey;
import nob.example.opappproject.entity.AuthorizationCodeInfo;
import nob.example.opappproject.entity.ClientInfo;
import nob.example.opappproject.entity.UserInfo;
import nob.example.opappproject.repository.AuthorizationCodeInfoRepository;
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

    @Autowired
    private AuthorizationCodeInfoRepository authorizationCodeInfoRepository;

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

        // 認可コードの文字長
        final int AUTHORIZATION_CODE_LENGTH = 32;
        // 認可コードに使われる文字一覧
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        // 認可コードの有効期限[分]
        final int EXPIRATION_MINUTE = 10;

        // クレデンシャル検証
        UserCredentialSelectKey userCredentialSelectKey = new UserCredentialSelectKey();
        userCredentialSelectKey.setUserId(certificateInModel.getUserId());
        userCredentialSelectKey.setPassword(certificateInModel.getPassword());
        List<UserInfo> userInfoList = userInfoRepository.selectUserCredential(userCredentialSelectKey);
        // ヒットした件数が1件でなければ認証失敗とする
        if (userInfoList.size() != 1) {
            System.out.println("認証失敗"); // TODO 例外作成
        }

        // 認可コード発行
        Random random = new Random();
        StringBuilder authorizationCodeBuilder = new StringBuilder();

        for (int i = 0; i < AUTHORIZATION_CODE_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            authorizationCodeBuilder.append(CHARACTERS.charAt(index));
        }

        // 認可コード
        String authorizationCode = authorizationCodeBuilder.toString();

        // 現在日時を取得し、10分後を有効期限とする
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.add(Calendar.MINUTE, EXPIRATION_MINUTE);
        Date expirationDateTime = calendar.getTime();

        // 認可コード情報テーブルに登録する内容を作成
        AuthorizationCodeInfo authorizationCodeInfo = new AuthorizationCodeInfo();
        authorizationCodeInfo.setCodeValue(authorizationCode);
        authorizationCodeInfo.setCodeChallenge(certificateInModel.getCodeChallenge());
        authorizationCodeInfo.setExpirationDateTime(expirationDateTime);
        authorizationCodeInfo.setIsDeleted(false);

        // 認可コード情報登録
        authorizationCodeInfoRepository.insert(authorizationCodeInfo);

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
    public IssueTokenOutModel issueToken(IssueTokenInModel issueTokenInModel) {

        // 保存されている認可コードを検索
        AuthorizationCodeInfoSelectKey authorizationCodeInfoSelectKey = new AuthorizationCodeInfoSelectKey();
        authorizationCodeInfoSelectKey.setCodeValue(issueTokenInModel.getAuthorizationCode());
        authorizationCodeInfoSelectKey.setNowDate(new Date());
        List<AuthorizationCodeInfo> authorizationCodeInfoList = authorizationCodeInfoRepository
                .selectAuthorizationCode(authorizationCodeInfoSelectKey);
        // 結果が1件でなければエラー
        if (authorizationCodeInfoList.size() != 1) {
            System.out.println("認証失敗"); // TODO 例外作成
        }

        // codeVerifier検証
        MessageDigest sha256;
        try {
            // inModelのcodeVerifierをエンコード
            sha256 = MessageDigest.getInstance("SHA-256");
            byte[] sha256Byte = sha256.digest(issueTokenInModel.getCodeVerifier().getBytes());
            HexFormat hex = HexFormat.of().withLowerCase();
            String targetCodeChallenge = hex.formatHex(sha256Byte);
            // DBに保存されているcodeChallengeと合致しなければエラー
            if (!targetCodeChallenge.equals(authorizationCodeInfoList.get(0).getCodeChallenge())) {
                System.out.println("認証失敗"); // TODO 例外作成
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // TODO アクセストークン、リフレッシュトークン、IDトークン作成

        return new IssueTokenOutModel();
    }
}
