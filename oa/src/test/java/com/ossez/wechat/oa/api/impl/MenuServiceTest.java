package com.ossez.wechat.oa.api.impl;

import com.google.inject.Inject;
import com.ossez.wechat.common.exception.DataStructureException;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.model.WeChatStatus;
import com.ossez.wechat.common.model.req.MenuRequest;
import com.ossez.wechat.common.model.req.MenuRequest.Button;
import com.ossez.wechat.common.model.res.DataCubeArticle;
import com.ossez.wechat.common.model.res.DataCubeUser;
import com.ossez.wechat.oa.api.impl.okhttp.WeChatDataCubeService;
import com.ossez.wechat.oa.api.impl.okhttp.WeChatMenuService;
import com.ossez.wechat.oa.api.test.TestBase;
import com.ossez.wechat.oa.api.test.TestConfigStorage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for datacube API
 *
 * @author YuCheng
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MenuServiceTest extends TestBase {
    private static final Logger log = LoggerFactory.getLogger(TestBase.class);

    @Inject
    protected TestConfigStorage testConfigStorage;

    @Inject
    protected WeChatMenuService weChatMenuService;

    @Test
    public void testCreate() throws WxErrorException, DataStructureException {
        MenuRequest menuRequest = new MenuRequest();

        Button button = new Button();
        button.setType("view");
        button.setName("USVisaTrack");
        button.setUrl("https://www.usvisatrack.com/");

        List<Button> buttonList = new ArrayList<>();
        buttonList.add(button);

        menuRequest.setButtonList(buttonList);

        WeChatStatus weChatStatus = weChatMenuService.create(menuRequest);

        assertThat(weChatStatus).isNotNull();


    }

}
