package com.ossez.wechat.oa.api.impl;

import com.google.inject.Inject;
import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.common.model.entity.builder.MenuButtonBuilder;
import com.ossez.wechat.common.model.entity.menu.WxMenu;
import com.ossez.wechat.common.model.entity.menu.MenuButton;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.test.ApiTestModule;
import com.ossez.wechat.oa.bean.menu.WxMpGetSelfMenuInfoResult;
import com.ossez.wechat.oa.bean.menu.WxMpMenu;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * 测试菜单
 *
 * @author chanjarster
 * @author Binary Wang
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpMenuServiceImplTest {

  @Inject
  protected WeChatOfficialAccountService wxService;
  private String menuId = null;

  @Test(dataProvider = "menu")
  public void testMenuCreate(WxMenu wxMenu) throws WxErrorException {
    System.out.println(wxMenu.toJson());
    this.wxService.getMenuService().menuCreate(wxMenu);
  }

  @Test
  public void testMenuTryMatch() throws Exception {
    WxMenu menu = this.wxService.getMenuService().menuTryMatch("...");
    System.out.println(menu);
  }

  @Test
  public void testGetSelfMenuInfo() throws Exception {
    WxMpGetSelfMenuInfoResult selfMenuInfo = this.wxService.getMenuService().getSelfMenuInfo();
    System.out.println(selfMenuInfo);
  }

  @Test
  public void testCreateConditionalMenu() throws WxErrorException {
    String json = "{\n" +
      " 	\"button\":[\n" +
      " 	{	\n" +
      "    	\"type\":\"click\",\n" +
      "    	\"name\":\"今日歌曲\",\n" +
      "     	\"key\":\"V1001_TODAY_MUSIC\" \n" +
      "	},\n" +
      "	{ \n" +
      "		\"name\":\"菜单\",\n" +
      "		\"sub_button\":[\n" +
      "		{	\n" +
      "			\"type\":\"view\",\n" +
      "			\"name\":\"搜索\",\n" +
      "			\"url\":\"http://www.soso.com/\"\n" +
      "		},\n" +
      "		{\n" +
      "			\"type\":\"view\",\n" +
      "			\"name\":\"视频\",\n" +
      "			\"url\":\"http://v.qq.com/\"\n" +
      "		},\n" +
      "		{\n" +
      "			\"type\":\"click\",\n" +
      "			\"name\":\"赞一下我们\",\n" +
      "			\"key\":\"V1001_GOOD\"\n" +
      "		}]\n" +
      " }],\n" +
      "\"matchrule\":{\n" +
      "  \"tag_id\":\"2\",\n" +
      "  \"sex\":\"1\",\n" +
      "  \"country\":\"中国\",\n" +
      "  \"province\":\"广东\",\n" +
      "  \"city\":\"广州\",\n" +
      "  \"client_platform_type\":\"2\",\n" +
      "  \"language\":\"zh_CN\"\n" +
      "  }\n" +
      "}";

    this.menuId = this.wxService.getMenuService().menuCreate(json);
    System.out.println(this.menuId);
  }

  @Test
  public void testMultiCreateConditionalMenu() throws WxErrorException {
    String json = "{\n" +
      " 	\"button\":[\n" +
      " 	{	\n" +
      "    	\"type\":\"click\",\n" +
      "    	\"name\":\"今日歌曲\",\n" +
      "     	\"key\":\"V1001_TODAY_MUSIC\" \n" +
      "	},\n" +
      "	{ \n" +
      "		\"name\":\"菜单\",\n" +
      "		\"sub_button\":[\n" +
      "		{	\n" +
      "			\"type\":\"view\",\n" +
      "			\"name\":\"搜索\",\n" +
      "			\"url\":\"http://www.soso.com/\"\n" +
      "		},\n" +
      "		{\n" +
      "			\"type\":\"view\",\n" +
      "			\"name\":\"视频\",\n" +
      "			\"url\":\"http://v.qq.com/\"\n" +
      "		},\n" +
      "		{\n" +
      "			\"type\":\"click\",\n" +
      "			\"name\":\"赞一下我们\",\n" +
      "			\"key\":\"V1001_GOOD\"\n" +
      "		}]\n" +
      " }],\n" +
      "\"matchrule\":{\n" +
      "  \"tag_id\":\"2\",\n" +
      "  \"sex\":\"1\",\n" +
      "  \"country\":\"中国\",\n" +
      "  \"province\":\"广东\",\n" +
      "  \"city\":\"广州\",\n" +
      "  \"client_platform_type\":\"2\",\n" +
      "  \"language\":\"zh_CN\"\n" +
      "  }\n" +
      "}";
    this.menuId = this.wxService.getMenuService().menuCreate(json);
    System.out.println(this.menuId);
  }

  @Test(dependsOnMethods = {"testCreateConditionalMenu"})
  public void testMenuGet_AfterCreateConditionalMenu() throws WxErrorException {
    WxMpMenu wxMenu = this.wxService.getMenuService().menuGet();
    Assert.assertNotNull(wxMenu);
    System.out.println(wxMenu.toJson());
    Assert.assertNotNull(wxMenu.getConditionalMenu().get(0).getRule().getTagId());
  }

  @Test(dependsOnMethods = {"testCreateConditionalMenu"})
  public void testDeleteConditionalMenu() throws WxErrorException {
    this.wxService.getMenuService().menuDelete(menuId);
  }

  @Test
  public void testCreateMenu_by_json() throws WxErrorException {
    String a = "{\n" +
      "  \"button\": [\n" +
      "    {\n" +
      "      \"type\": \"click\",\n" +
      "      \"name\": \"今日歌曲\",\n" +
      "      \"key\": \"V1001_TODAY_MUSIC\"\n" +
      "    },\n" +
      "    {\n" +
      "      \"name\": \"菜单\",\n" +
      "      \"sub_button\": [\n" +
      "        {\n" +
      "          \"type\": \"view\",\n" +
      "          \"name\": \"搜索\",\n" +
      "          \"url\": \"http://www.soso.com/\"\n" +
      "        },\n" +
      "        {\n" +
      "          \"type\": \"miniprogram\",\n" +
      "          \"name\": \"wxa\",\n" +
      "          \"url\": \"http://mp.weixin.qq.com\",\n" +
      "          \"appid\": \"wx286b93c14bbf93aa\",\n" +
      "          \"pagepath\": \"pages/lunar/index.html\"\n" +
      "        },\n" +
      "        {\n" +
      "          \"type\": \"click\",\n" +
      "          \"name\": \"赞一下我们\",\n" +
      "          \"key\": \"V1001_GOOD\"\n" +
      "        }\n" +
      "      ]\n" +
      "    }\n" +
      "  ]\n" +
      "}";

    WxMenu menu = WxMenu.fromJson(a);
    System.out.println(menu.toJson());
    this.wxService.getMenuService().menuCreate(menu);
  }

  @Test(dependsOnMethods = {"testMenuCreate"})
  public void testMenuGet() throws WxErrorException {
    WxMpMenu wxMenu = this.wxService.getMenuService().menuGet();
    Assert.assertNotNull(wxMenu);
    System.out.println(wxMenu.toJson());
  }

  @Test(dependsOnMethods = {"testMenuGet", "testMenuCreate"})
  public void testMenuDelete() throws WxErrorException {
    this.wxService.getMenuService().menuDelete();
  }

  @DataProvider(name = "menu")
  public Object[][] getMenu() {
    WxMenu menu = new WxMenu();
    MenuButton button1 = new MenuButtonBuilder().createMenuButton();
    button1.setType(WeChatConstant.MenuButtonType.CLICK);
    button1.setName("今日歌曲");
    button1.setKey("V1001_TODAY_MUSIC");

//    WxMenuButton button2 = new WxMenuButton();
//    button2.setType(WxConsts.MenuButtonType.MINIPROGRAM);
//    button2.setName("小程序");
//    button2.setAppId("wx286b93c14bbf93aa");
//    button2.setPagePath("pages/lunar/index.html");
//    button2.setUrl("http://mp.weixin.qq.com");

    MenuButton button3 = new MenuButtonBuilder().createMenuButton();
    button3.setName("菜单");

    menu.getButtons().add(button1);
//    menu.getButtons().add(button2);
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


}
