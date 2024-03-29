package nob.example.rpappproject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import nob.example.rpappproject.dto.FetchUserInfoInModel;
import nob.example.rpappproject.dto.FetchUserInfoOutModel;
import nob.example.rpappproject.rest.dto.OpFetchUserInfoOutModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * AuthorizationServiceImplのテストクラスです。
 * 
 * @author nob
 */
@SpringBootTest
public class AuthorizationServiceImplTest {

    @Autowired
    private AuthorizationService authorizationService;

    /**
     * fetchUserInfoのテスト 正常系
     * 
     * @throws Exception
     */
    @Test
    public void test_fetchUserInfo_success() {

        // 入力値の作成
        FetchUserInfoInModel fetchUserInfoInModel = new FetchUserInfoInModel();
        fetchUserInfoInModel.setUserId("testNob");

        // モック返却値の作成
        OpFetchUserInfoOutModel opFetchUserInfoOutModel = new OpFetchUserInfoOutModel();
        opFetchUserInfoOutModel.setUserId("testNob");
        opFetchUserInfoOutModel.setUserName("testNobuhiro");

        // リクエストURL
        String url = "http://localhost:8081/api/op/userinfo";

        // モックサーバの作成 // TODO RestTemplateはAutowiredできた方がいいかも
        MockRestServiceServer mockRestServiceServer = MockRestServiceServer.bindTo(new RestTemplate()).build();
        mockRestServiceServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(opFetchUserInfoOutModel.toString(), MediaType.APPLICATION_JSON));

        try {
            FetchUserInfoOutModel fetchUserInfoOutModel = authorizationService.fetchUserInfo(fetchUserInfoInModel);
            assertEquals(fetchUserInfoOutModel.getUserId(), "testNob");
            assertEquals(fetchUserInfoOutModel.getUserName(), "testNobuhiro");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }
}
