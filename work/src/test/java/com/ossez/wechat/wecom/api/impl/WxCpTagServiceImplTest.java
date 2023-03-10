package com.ossez.wechat.wecom.api.impl;

import com.google.common.base.Splitter;
import com.google.inject.Inject;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.wecom.api.ApiTestModule;
import com.ossez.wechat.wecom.api.WxCpService;
import com.ossez.wechat.wecom.api.WxCpTagService;
import com.ossez.wechat.wecom.bean.WxCpTag;
import com.ossez.wechat.wecom.bean.WxCpTagAddOrRemoveUsersResult;
import com.ossez.wechat.wecom.bean.WxCpTagGetResult;
import com.ossez.wechat.wecom.bean.WxCpUser;
import com.ossez.wechat.wecom.constant.WxCpApiPathConsts;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

/**
 * <pre>
 * Created by Binary Wang on 2017-6-25.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxCpTagServiceImplTest {
  /**
   * The Wx service.
   */
  @Inject
  protected WxCpService wxService;

  /**
   * The Config storage.
   */
  @Inject
  protected ApiTestModule.WxXmlCpInMemoryConfigStorage configStorage;

  private String tagId;

  /**
   * Test create.
   *
   * @throws Exception the exception
   */
  @Test
  public void testCreate() throws Exception {
    this.tagId = this.wxService.getTagService().create("测试标签" + System.currentTimeMillis(), null);
    System.out.println(this.tagId);
  }

  /**
   * Test update.
   *
   * @throws Exception the exception
   */
  @Test(dependsOnMethods = "testCreate")
  public void testUpdate() throws Exception {
    this.wxService.getTagService().update(this.tagId, "测试标签-改名" + System.currentTimeMillis());
  }

  /**
   * Test list all.
   *
   * @throws Exception the exception
   */
  @Test(dependsOnMethods = {"testUpdate", "testCreate"})
  public void testListAll() throws Exception {
    List<WxCpTag> tags = this.wxService.getTagService().listAll();
    assertNotEquals(tags.size(), 0);
  }

  /**
   * Test add users 2 tag.
   *
   * @throws Exception the exception
   */
  @Test(dependsOnMethods = {"testListAll", "testUpdate", "testCreate"})
  public void testAddUsers2Tag() throws Exception {
    List<String> userIds = Splitter.on("|").splitToList(this.configStorage.getUserId());
    WxCpTagAddOrRemoveUsersResult result = this.wxService.getTagService().addUsers2Tag(this.tagId, userIds, null);
    assertEquals(result.getErrCode(), Integer.valueOf(0));
  }

  /**
   * Test list users by tag id.
   *
   * @throws Exception the exception
   */
  @Test(dependsOnMethods = {"testAddUsers2Tag", "testListAll", "testUpdate", "testCreate"})
  public void testListUsersByTagId() throws Exception {
    List<WxCpUser> users = this.wxService.getTagService().listUsersByTagId(this.tagId);
    assertNotEquals(users.size(), 0);
  }

  /**
   * Test remove users from tag.
   *
   * @throws Exception the exception
   */
  @Test(dependsOnMethods = {"testListUsersByTagId", "testAddUsers2Tag", "testListAll", "testUpdate", "testCreate"})
  public void testRemoveUsersFromTag() throws Exception {
    List<String> userIds = Splitter.on("|").splitToList(this.configStorage.getUserId());
    WxCpTagAddOrRemoveUsersResult result = this.wxService.getTagService().removeUsersFromTag(this.tagId, userIds, null);
    assertEquals(result.getErrCode(), Integer.valueOf(0));
  }

  /**
   * Test delete.
   *
   * @throws Exception the exception
   */
  @Test(dependsOnMethods = {"testRemoveUsersFromTag", "testListUsersByTagId", "testAddUsers2Tag", "testListAll",
    "testUpdate", "testCreate"})
  public void testDelete() throws Exception {
    this.wxService.getTagService().delete(this.tagId);
  }

  /**
   * Test get.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGet() throws WxErrorException {
    String apiResultJson = "{\"errcode\": 0,\"errmsg\": \"ok\",\"userlist\": [{\"userid\": \"0124035\",\"name\": " +
      "\"王五\"},{\"userid\": \"0114035\",\"name\": \"梦雪\"}],\"partylist\": [9576,9567,9566],\"tagname\": \"测试标签-001\"}";
    WxCpService wxService = mock(WxCpService.class);
    when(wxService.get(String.format(wxService.getWxCpConfigStorage().getApiUrl(WxCpApiPathConsts.Tag.TAG_GET), 150),
      null)).thenReturn(apiResultJson);
    when(wxService.getTagService()).thenReturn(new WxCpTagServiceImpl(wxService));

    WxCpTagService wxCpTagService = wxService.getTagService();

    WxCpTagGetResult wxCpTagGetResult = wxCpTagService.get(String.valueOf(150));

    assertEquals(0, wxCpTagGetResult.getErrcode().intValue());

    assertEquals(2, wxCpTagGetResult.getUserlist().size());
    assertEquals(3, wxCpTagGetResult.getPartylist().size());
    assertEquals("测试标签-001", wxCpTagGetResult.getTagname());

  }

}
