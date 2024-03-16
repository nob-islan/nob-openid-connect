package nob.example.opproject.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import nob.example.opproject.TestConfig;
import nob.example.opproject.dto.UserInfoSearchConditionDto;
import nob.example.opproject.entity.UserInfo;

/**
 * UserInfoDaoImplのテストクラスです。
 * 
 * @author nob
 */
@SpringBootTest
public class UserInfoDaoImplTest {

    @BeforeEach
    private void testConfig() throws Exception {
        // テストデータ作成
        TestConfig.testDataSetup("dao", "userInfoDaoImplTest");
    }

    @Autowired
    private UserInfoDao userInfoDao;

    /**
     * selectByConditionのテスト 正常系
     * 
     */
    @Test
    public void test_selectByCondition_success() {

        // 検索条件を設定
        UserInfoSearchConditionDto userInfoSearchConditionDto = new UserInfoSearchConditionDto();
        userInfoSearchConditionDto.setLoginId("nob");

        // テスト実行
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        try {
            userInfoList = userInfoDao.selectByCondition(userInfoSearchConditionDto);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        // 結果の検証
        assertEquals(1, userInfoList.size());
        assertEquals("1", userInfoList.get(0).getUserId());
        assertEquals("nob", userInfoList.get(0).getLoginId());
        assertEquals("p@ssw0rd", userInfoList.get(0).getPassword());
        assertEquals("nobuhiro", userInfoList.get(0).getUserName());
    }
}
