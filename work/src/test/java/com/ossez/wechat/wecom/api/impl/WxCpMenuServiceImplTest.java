package com.ossez.wechat.wecom.api.impl;

import com.google.inject.Inject;
import com.ossez.wechat.common.model.entity.builder.MenuButtonBuilder;
import com.ossez.wechat.common.model.entity.menu.WxMenu;
import com.ossez.wechat.common.model.entity.menu.MenuButton;
import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.wecom.api.ApiTestModule;
import com.ossez.wechat.wecom.api.WxCpService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * <pre>
 *
 * Created by Binary Wang on 2017-6-25.
 * @author  <a href="https://github.com/binarywang">Binary Wang</a> </pre>
 */
@Guice(modules = ApiTestModule.class)
public class WxCpMenuServiceImplTest {
  /**
   * The Wx service.
   */
  @Inject
  protected WxCpService wxService;

  /**
   * Menu data object [ ] [ ].
   *
   * @return the object [ ] [ ]
   */
  @DataProvider
  public Object[][] menuData() {
    WxMenu menu = new WxMenu();
    MenuButton button1 = new MenuButtonBuilder().createMenuButton();
    Object WxConsts;
    button1.setType(WeChatConstant.MenuButtonType.CLICK);
    button1.setName("今日歌曲");
    button1.setKey("V1001_TODAY_MUSIC");

    MenuButton button2 = new MenuButtonBuilder().createMenuButton();
    button2.setType(WeChatConstant.MenuButtonType.CLICK);
    button2.setName("歌手简介");
    button2.setKey("V1001_TODAY_SINGER");

    MenuButton button3 = new MenuButtonBuilder().createMenuButton();
    button3.setName("菜单");

    menu.getButtons().add(button1);
    menu.getButtons().add(button2);
    menu.getButtons().add(button3);

    MenuButton button31 = new MenuButtonBuilder().createMenuButton();
    button31.setType(WeChatConstant.MenuButtonType.VIEW);
    button31.setName("搜索");
    button31.setUrl("http://www.soso.com/");

    MenuButton button32 = new MenuButtonBuilder().createMenuButton();
    button32.setType(WeChatConstant.MenuButtonType.VIEW);
    button32.setName("视频");
    button32.setUrl("http://v.qq.com/");

    MenuButton button33 = new MenuButtonBuilder().createMenuButton();
    button33.setType(WeChatConstant.MenuButtonType.CLICK);
    button33.setName("赞一下我们");
    button33.setKey("V1001_GOOD");

//    button3.getSubButtons().add(button31);
//    button3.getSubButtons().add(button32);
//    button3.getSubButtons().add(button33);

    return new Object[][]{
      new Object[]{
        menu
      }
    };

  }

  /**
   * Test create.
   *
   * @param wxMenu the wx menu
   * @throws Exception the exception
   */
  @Test(dataProvider = "menuData")
  public void testCreate(WxMenu wxMenu) throws Exception {
    this.wxService.getMenuService().create(wxMenu);
  }

  /**
   * Test get.
   *
   * @throws Exception the exception
   */
  @Test(dependsOnMethods = "testCreate")
  public void testGet() throws Exception {
    WxMenu menu = this.wxService.getMenuService().get();
    assertNotNull(menu);
    System.out.println(menu.toJson());
  }

  /**
   * Test delete.
   *
   * @throws Exception the exception
   */
  @Test(dependsOnMethods = {"testGet", "testCreate"})
  public void testDelete() throws Exception {
    this.wxService.getMenuService().delete();
  }

}
