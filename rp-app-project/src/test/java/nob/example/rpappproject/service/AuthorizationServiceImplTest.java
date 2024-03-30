package nob.example.rpappproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import nob.example.rpappproject.dto.FetchUserInfoInModel;
import nob.example.rpappproject.dto.FetchUserInfoOutModel;
import nob.example.rpappproject.rest.dto.OpFetchUserInfoResponse;

/**
 * AuthorizationServiceImplのテストクラスです。
 * 
 * @author nob
 */
@SpringBootTest
public class AuthorizationServiceImplTest {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * fetchUserInfoのテスト 正常系
     * 
     * @throws Exception
     */
    @Test
    public void test_fetchUserInfo_success() throws Exception {

        // 入力値の作成
        FetchUserInfoInModel fetchUserInfoInModel = new FetchUserInfoInModel();
        fetchUserInfoInModel.setUserId("testNob");

        // モック返却値の作成
        OpFetchUserInfoResponse opFetchUserInfoResponse = new OpFetchUserInfoResponse();
        opFetchUserInfoResponse.setUserId("testNob");
        opFetchUserInfoResponse.setUserName("testNobuhiro");
        ObjectMapper mapper = new ObjectMapper();
        String response = mapper.writeValueAsString(opFetchUserInfoResponse);

        // リクエストURL
        String url = "http://localhost:8081/api/op/userinfo";

        // モックサーバの作成
        MockRestServiceServer mockRestServiceServer = MockRestServiceServer.bindTo(restTemplate).build();
        mockRestServiceServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST)).andRespond(
                withSuccess(response, MediaType.APPLICATION_JSON));

        try {
            // サービス呼び出し
            FetchUserInfoOutModel fetchUserInfoOutModel = authorizationService.fetchUserInfo(fetchUserInfoInModel);
            // 結果のassert
            assertEquals("testNob", fetchUserInfoOutModel.getUserId());
            assertEquals("testNobuhiro", fetchUserInfoOutModel.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
