package com.ossez.wechat.oa.api.impl;

import com.google.inject.Inject;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.model.WeChatStatus;
import com.ossez.wechat.common.model.req.ButtonBuilder;
import com.ossez.wechat.common.model.req.MenuRequest;
import com.ossez.wechat.common.model.req.MenuRequest.Button;
import com.ossez.wechat.oa.api.impl.okhttp.WeChatMenuService;
import com.ossez.wechat.oa.api.test.TestBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for datacube API
 *
 * @author YuCheng
 */
@TestInstance(Lifecycle.PER_CLASS)
public class MenuServiceTest extends TestBase {
    private static final Logger log = LoggerFactory.getLogger(TestBase.class);
    @Inject
    protected WeChatMenuService weChatMenuService;

    /**
     * Test Create Menu
     *
     * @throws WxErrorException
     */
    @Test
    public void testCreate() throws WxErrorException {
        log.debug("Create WeChat Offical Account Menun Test");
        MenuRequest menuRequest = new MenuRequest();

        List<Button> buttonList = new ArrayList<>();

        List<Button> menuLinkButtonList = new ArrayList<>();
        menuLinkButtonList.add(new ButtonBuilder()
                .setType("view")
                .setName("地产经纪")
                .setUrl("https://www.verani.com/").createButton());


        menuLinkButtonList.add(new ButtonBuilder()
                .setType("view")
                .setName("置业科普")
                .setUrl("https://www.isharkfly.com/c/realestate/8").createButton());

        menuLinkButtonList.add(new ButtonBuilder()
                .setType("view")
                .setName("USVisaTrack")
                .setUrl("https://www.usvisatrack.com/").createButton());

        Button buttonTop = new ButtonBuilder().createButton();
        buttonTop.setName("Shark 服务");
        buttonTop.setSubButtonList(menuLinkButtonList);
        buttonList.add(buttonTop);

        menuRequest.setButtonList(buttonList);

        WeChatStatus weChatStatus = weChatMenuService.create(menuRequest);

        assertThat(weChatStatus).isNotNull();


    }

}
