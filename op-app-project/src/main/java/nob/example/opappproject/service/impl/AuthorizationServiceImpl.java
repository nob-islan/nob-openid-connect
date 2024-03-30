package nob.example.opappproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nob.example.opappproject.dto.CertificationInModel;
import nob.example.opappproject.dto.CertificationOutModel;
import nob.example.opappproject.dto.FetchUserInfoInModel;
import nob.example.opappproject.dto.FetchUserInfoOutModel;
import nob.example.opappproject.dto.UserInfoSearchConditionDto;
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
    public CertificationOutModel certificate(CertificationInModel certificationInModel) {

        // TODO 実装
        CertificationOutModel certificationOutModel = new CertificationOutModel();
        certificationOutModel.setIsCertificated(true);

        return certificationOutModel;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public FetchUserInfoOutModel fetchUserInfo(FetchUserInfoInModel fetchUserInfoInModel) {

        // DBアクセス用のリクエストモデルを作成
        UserInfoSearchConditionDto userInfoSearchConditionDto = new UserInfoSearchConditionDto();
        userInfoSearchConditionDto.setUserId(fetchUserInfoInModel.getUserId());

        // repository呼び出し // TODO 結果が0件だった場合の例外処理
        List<UserInfo> userInfoList = userInfoRepository.selectByCondition(userInfoSearchConditionDto);

        // レスポンス作成
        FetchUserInfoOutModel fetchUserInfoOutModel = new FetchUserInfoOutModel();
        fetchUserInfoOutModel.setUserId(userInfoList.get(0).getUserId());
        fetchUserInfoOutModel.setUserName(userInfoList.get(0).getUserName());

        return fetchUserInfoOutModel;
    }
}
