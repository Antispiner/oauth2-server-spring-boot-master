package com.internalproject.api.controllers;

import com.internalproject.api.Application;
import com.internalproject.api.domain.dto.UserDto;
import com.internalproject.api.exceptions.models.ApiException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@WebAppConfiguration
class UserControllerTest {

    @Autowired
    WebApplicationContext context;
    @Autowired
    FilterChainProxy springSecurityFilterChain;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(springSecurityFilterChain).build();
    }



    private String obtainAccessToken(final String username, final String password) throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", username);
        params.add("password", password);
        ResultActions result = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/login").params(params)
                        .with(httpBasic("hiraeth-read-write-client","hiraeth_client_secret"))
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }

    @Test
    public void whenPostRequestRegistration_withNotFullBody_badRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\n\"email\": \"admin@test.com\"\n}"))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void whenPostRequestRegistration_withExistedEmailOrUserName_InternalServerError() throws Exception {
        UserDto dto = new UserDto();
        dto.setFirstName("");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\n" +
                        "  \"email\": \"admin@test.com\",\n" +
                        "  \"firstName\": \"firstName\",\n" +
                        "  \"lastName\": \"lastName\",\n" +
                        "  \"password\": \"password\",\n" +
                        "  \"username\": \"admin\"\n" +
                        "}")
        )
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ApiException));
    }
}