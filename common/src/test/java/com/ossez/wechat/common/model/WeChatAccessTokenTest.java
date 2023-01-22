package com.ossez.wechat.common.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ossez.wechat.common.model.WeChatAccessToken;
import com.ossez.wechat.common.util.http.apache.ApacheHttpDnsClientBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for Access Token
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WeChatAccessTokenTest {
    final Logger log = LoggerFactory.getLogger(ApacheHttpDnsClientBuilder.class);
    private String accessTokenResponse;

    @BeforeEach
    protected void setUp() throws Exception {
        accessTokenResponse = "{\"access_token\":\"ACCESS_TOKEN\",\"expires_in\":7200}";
    }

    @AfterEach
    protected void tearDown() throws Exception {
    }


    /**
     * Test Json String to Object
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testAccessTokenJsonToObj() throws JsonProcessingException {
        log.debug("Test testAccessTokenJsonToObj");

        ObjectMapper mapper = new ObjectMapper();
        WeChatAccessToken accessToken = mapper.readValue(accessTokenResponse, WeChatAccessToken.class);

        assertThat(accessToken.getAccessToken()).isEqualTo("ACCESS_TOKEN");
        assertThat(accessToken.getExpiresIn()).isEqualTo(7200);

    }

}
