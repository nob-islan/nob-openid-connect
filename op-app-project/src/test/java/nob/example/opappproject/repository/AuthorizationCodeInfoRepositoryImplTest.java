package nob.example.opappproject.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;
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

import nob.example.opappproject.dto.AuthorizationCodeInfoSelectKey;
import nob.example.opappproject.entity.AuthorizationCodeInfo;

/**
 * AuthorizationCodeInfoRepositoryImplのテストクラスです。
 * 
 * @author nob
 */
@SpringBootTest
@Testcontainers(disabledWithoutDocker = true)
public class AuthorizationCodeInfoRepositoryImplTest {

    // データベースのコンテナイメージなど、DB構築に必要な設定値です。
    static final DockerImageName MARIA_DB_IMAGE_NAME = DockerImageName.parse("mariadb").withTag("10.5");
    static final String DATABASE_NAME = "OPDB";
    static final String USER_NAME = "root";
    static final String PASSWORD = "";

    // テスト用DBコンテナを作成
    @SuppressWarnings("resource")
    @Container
    static final MariaDBContainer<?> mariaDbContainer = new MariaDBContainer<>(MARIA_DB_IMAGE_NAME)
            .withDatabaseName(DATABASE_NAME)
            .withUsername(USER_NAME)
            .withPassword(PASSWORD)
            .withInitScript("repository/authorizationcodeinfo/create_table.sql");

    // 接続情報などの設定値を投入
    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mariaDbContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mariaDbContainer::getUsername);
        registry.add("spring.datasource.password", mariaDbContainer::getPassword);
    }

    @Autowired
    private AuthorizationCodeInfoRepository authorizationCodeInfoRepository;

    /**
     * insertのテスト 正常系
     * 
     * @throws Exception
     */
    @Test
    public void test_insert_success() throws Exception {

        // 登録内容の作成
        AuthorizationCodeInfo authorizationCodeInfo = new AuthorizationCodeInfo();
        authorizationCodeInfo.setCodeValue("test_value");
        authorizationCodeInfo.setCodeChallenge("test_code_challenge");
        authorizationCodeInfo.setExpirationDateTime(new Date());
        authorizationCodeInfo.setIsDeleted(false);

        try {
            // repository呼び出し
            authorizationCodeInfoRepository.insert(authorizationCodeInfo);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * selectAuthorizationCodeのテスト 有効な認可コードが取得できる
     * 
     * @throws Exception
     */
    @Test
    public void test_selectAuthorizationCode_valid_code() throws Exception {

        // 検索条件の設定
        AuthorizationCodeInfoSelectKey authorizationCodeInfoSelectKey = new AuthorizationCodeInfoSelectKey();
        authorizationCodeInfoSelectKey.setCodeValue("testCodeValue1");
        authorizationCodeInfoSelectKey.setNowDate(new Date());

        try {
            // repository呼び出し
            List<AuthorizationCodeInfo> authorizationCodeInfoList = authorizationCodeInfoRepository
                    .selectAuthorizationCode(authorizationCodeInfoSelectKey);
            // 結果のassert
            assertEquals(1, authorizationCodeInfoList.size());
            assertEquals("testCodeValue1", authorizationCodeInfoList.get(0).getCodeValue());
            assertEquals("testCodeChallenge1", authorizationCodeInfoList.get(0).getCodeChallenge());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * selectAuthorizationCodeのテスト 認可コードの期限が切れている
     * 
     * @throws Exception
     */
    @Test
    public void test_selectAuthorizationCode_expired_code() throws Exception {

        // 検索条件の設定
        AuthorizationCodeInfoSelectKey authorizationCodeInfoSelectKey = new AuthorizationCodeInfoSelectKey();
        authorizationCodeInfoSelectKey.setCodeValue("testCodeValue2");
        authorizationCodeInfoSelectKey.setNowDate(new Date());

        try {
            // repository呼び出し
            List<AuthorizationCodeInfo> authorizationCodeInfoList = authorizationCodeInfoRepository
                    .selectAuthorizationCode(authorizationCodeInfoSelectKey);
            // 結果のassert
            assertEquals(0, authorizationCodeInfoList.size());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * selectAuthorizationCodeのテスト 認可コードが論理削除済み
     * 
     * @throws Exception
     */
    @Test
    public void test_selectAuthorizationCode_deleted_code() throws Exception {

        // 検索条件の設定
        AuthorizationCodeInfoSelectKey authorizationCodeInfoSelectKey = new AuthorizationCodeInfoSelectKey();
        authorizationCodeInfoSelectKey.setCodeValue("testCodeValue3");
        authorizationCodeInfoSelectKey.setNowDate(new Date());

        try {
            // repository呼び出し
            List<AuthorizationCodeInfo> authorizationCodeInfoList = authorizationCodeInfoRepository
                    .selectAuthorizationCode(authorizationCodeInfoSelectKey);
            // 結果のassert
            assertEquals(0, authorizationCodeInfoList.size());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * updateIsDeletedのテスト 正常系
     * 
     * @throws Exception
     */
    @Test
    public void test_updateIsDeleted_success() throws Exception {

        // 検索条件の設定
        AuthorizationCodeInfo authorizationCodeInfo = new AuthorizationCodeInfo();
        authorizationCodeInfo.setCodeValue("testCodeValue4");

        try {
            // repository呼び出し
            authorizationCodeInfoRepository.updateIsDeleted(authorizationCodeInfo);
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
