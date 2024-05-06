package nob.example.opappproject.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

import nob.example.opappproject.dto.RedirectUriSelectKey;
import nob.example.opappproject.entity.ClientInfo;

/**
 * ClientInfoRepositoryImplのテストクラスです。
 * 
 * @author nob
 */
@SpringBootTest
@Testcontainers(disabledWithoutDocker = true)
public class ClientInfoRepositoryImplTest {

    // データベースのコンテナイメージなど、DB構築に必要な設定値です。
    static final DockerImageName MARIA_DB_IMAGE_NAME = DockerImageName.parse("mariadb").withTag("10.5");
    static final String DATABASE_NAME = "OPDB";
    static final String USER_NAME = "root";
    static final String PASSWORD = "";

    // テスト用DBコンテナを作成
    @Container
    static final MariaDBContainer<?> mariaDbContainer = new MariaDBContainer<>(MARIA_DB_IMAGE_NAME)
            .withDatabaseName(DATABASE_NAME)
            .withUsername(USER_NAME)
            .withPassword(PASSWORD)
            .withInitScript("repository/clientinfo/create_table.sql");

    // 接続情報などの設定値を投入
    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mariaDbContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mariaDbContainer::getUsername);
        registry.add("spring.datasource.password", mariaDbContainer::getPassword);
    }

    @Autowired
    private ClientInfoRepository clientInfoRepository;

    /**
     * selectRedirectUriのテスト 正常系
     * 
     * @throws Exception
     */
    @Test
    public void test_selectRedirectUri_success() throws Exception {

        // 検索条件の設定
        RedirectUriSelectKey redirectUriSelectKey = new RedirectUriSelectKey();
        redirectUriSelectKey.setClientId("testClient");

        try {
            // repository呼び出し
            List<ClientInfo> clientInfoList = clientInfoRepository.selectRedirectUri(redirectUriSelectKey);
            // 結果のassert
            assertEquals(1, clientInfoList.size());
            assertEquals("http://example.nob/sample",
                    clientInfoList.get(0).getRedirectUri());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
