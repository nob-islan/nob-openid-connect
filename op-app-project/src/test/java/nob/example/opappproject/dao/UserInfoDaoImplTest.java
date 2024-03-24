package nob.example.opappproject.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import nob.example.opappproject.dto.UserInfoSearchConditionDto;
import nob.example.opappproject.entity.UserInfo;

/**
 * UserInfoDaoImplのテストクラスです。
 * 
 * @author nob
 */
@SpringBootTest
@Testcontainers(disabledWithoutDocker = true)
public class UserInfoDaoImplTest {

    // データベースのコンテナイメージなど、DB構築に必要な設定値です。
    static final DockerImageName MARIA_DB_IMAGE_NAME = DockerImageName.parse("mariadb").withTag("10.5");
    static final String DATABASE_NAME = "OPDB";
    static final String USER_NAME = "root";
    static final String PASSWORD = "";

    // テスト用DBコンテナを作成します。
    @Container
    static final MariaDBContainer<?> mariaDbContainer = new MariaDBContainer<>(MARIA_DB_IMAGE_NAME)
            .withDatabaseName(DATABASE_NAME)
            .withUsername(USER_NAME)
            .withPassword(PASSWORD)
            .withInitScript("userinfo/create_table.sql");

    // 接続情報などの設定値を投入します。
    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mariaDbContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mariaDbContainer::getUsername);
        registry.add("spring.datasource.password", mariaDbContainer::getPassword);
    }

    @Autowired
    private UserInfoDao userInfoDao;

    /**
     * selectAllのテスト 正常系
     *
     */
    @Test
    public void test_selectByCondition_success() {

        // 検索条件の設定
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

        assertEquals(1, userInfoList.size());
        assertEquals("1", userInfoList.get(0).getUserId());
        assertEquals("nob", userInfoList.get(0).getLoginId());
        assertEquals("p@ssw0rd", userInfoList.get(0).getPassword());
        assertEquals("nobuhiro", userInfoList.get(0).getUserName());
    }
}