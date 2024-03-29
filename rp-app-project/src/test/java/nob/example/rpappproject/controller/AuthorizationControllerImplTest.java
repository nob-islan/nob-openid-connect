package nob.example.rpappproject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * AuthorizationControllerImplのテストクラスです。
 * 
 * @author nob
 */
@SpringBootTest
public class AuthorizationControllerImplTest {

    @Autowired
    private AuthorizationController authorizationController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(authorizationController).build();
    }

    /**
     * redirectAuthorizationのテスト 正常系
     * 
     * @throws Exception
     */
    @Test
    public void test_redirectAuthorization_success() throws Exception {

        mockMvc.perform(get("/api/rp/redirect/authorization"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:http://localhost:8081/api/op/authorization"));
    }
}
