package nob.example.opappproject.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import nob.example.opappproject.constants.ErrorConst;
import nob.example.opappproject.constants.JwtConst;
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
import nob.example.opappproject.exceptions.OpAuthException;
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
     * @throws OpAuthException
     * 
     */
    @Override
    public AuthorizeOutModel authorize(AuthorizeInModel authorizeInModel) throws OpAuthException {

        // DBからリダイレクトURI取得
        RedirectUriSelectKey redirectUriSelectKey = new RedirectUriSelectKey();
        redirectUriSelectKey.setClientId(authorizeInModel.getClientId());
        List<ClientInfo> clientInfoList = clientInfoRepository.selectRedirectUri(redirectUriSelectKey);
        // DBから取得したリダイレクトURLとinModelのリダイレクトURLとを比較
        if (!clientInfoList.get(0).getRedirectUri().equals(authorizeInModel.getRedirectUri())) {
            throw (new OpAuthException(ErrorConst.Code.INVALID_REDIRECT_URI, ErrorConst.Message.BASE));
        }

        // 検証OKであればリダイレクトURIをそのまま返却
        AuthorizeOutModel authorizeOutModel = new AuthorizeOutModel();
        authorizeOutModel.setRedirectUri(authorizeInModel.getRedirectUri());

        return authorizeOutModel;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws OpAuthException
     * 
     */
    @Override
    public CertificateOutModel certificate(CertificateInModel certificateInModel) throws OpAuthException {

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
            throw (new OpAuthException(ErrorConst.Code.INVALID_CREDENTIAL, ErrorConst.Message.BASE));
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
        authorizationCodeInfo.setUserId(certificateInModel.getUserId());
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
     * @throws OpAuthException
     * 
     */
    @Override
    public IssueTokenOutModel issueToken(IssueTokenInModel issueTokenInModel) throws OpAuthException {

        // 保存されている認可コードを検索
        AuthorizationCodeInfoSelectKey authorizationCodeInfoSelectKey = new AuthorizationCodeInfoSelectKey();
        authorizationCodeInfoSelectKey.setCodeValue(issueTokenInModel.getAuthorizationCode());
        authorizationCodeInfoSelectKey.setNowDate(new Date());
        List<AuthorizationCodeInfo> authorizationCodeInfoList = authorizationCodeInfoRepository
                .selectAuthorizationCode(authorizationCodeInfoSelectKey);
        // 結果が1件でなければエラー
        if (authorizationCodeInfoList.size() != 1) {
            throw (new OpAuthException(ErrorConst.Code.INVALID_AUTHORIZATION_CODE, ErrorConst.Message.BASE));
        }

        // codeVerifier検証
        String targetCodeChallenge = DigestUtils.sha256Hex(issueTokenInModel.getCodeVerifier());
        // DBに保存されているcodeChallengeと合致しなければエラー
        if (!targetCodeChallenge.equals(authorizationCodeInfoList.get(0).getCodeChallenge())) {
            throw (new OpAuthException(ErrorConst.Code.INVALID_CODE_VERIFIER, ErrorConst.Message.BASE));
        }

        // 検証済みの認可コードを削除
        AuthorizationCodeInfo authorizationCodeInfo = new AuthorizationCodeInfo();
        authorizationCodeInfo.setCodeValue(authorizationCodeInfoList.get(0).getCodeValue());
        authorizationCodeInfoRepository.updateIsDeleted(authorizationCodeInfo);

        // アクセストークンの各クレーム作成
        Algorithm algorithm = Algorithm.HMAC256(JwtConst.SECRET_KEY);
        Date issuedAt = new Date(); // トークン発行時刻
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(issuedAt);
        calendar.add(Calendar.DATE, JwtConst.VALIDITY_PERIOD_DAY);
        Date expiresAt = calendar.getTime(); // 有効期限
        String audience = JwtConst.AUDIENCE; // トークン行使先
        String issuer = JwtConst.ISSUER; // トークン発行者の識別子
        String subject = authorizationCodeInfoList.get(0).getUserId(); // ユーザの識別子
        // アクセストークン作成
        String accessToken = JWT.create().withExpiresAt(expiresAt).withIssuedAt(issuedAt).withAudience(audience)
                .withIssuer(issuer).withSubject(subject).sign(algorithm);

        // IDトークンのクレーム作成
        String idToken = JWT.create().withExpiresAt(expiresAt).withIssuedAt(issuedAt).withAudience(audience)
                .withIssuer(issuer).withSubject(subject).sign(algorithm);

        // 返却値の作成
        IssueTokenOutModel issueTokenOutModel = new IssueTokenOutModel();
        issueTokenOutModel.setAccessToken(accessToken);
        issueTokenOutModel.setIdToken(idToken);

        return issueTokenOutModel;
    }
}
