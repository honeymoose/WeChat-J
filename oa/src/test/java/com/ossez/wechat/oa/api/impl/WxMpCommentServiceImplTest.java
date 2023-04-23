package com.ossez.wechat.oa.api.impl;

import com.google.inject.Inject;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.WxMpCommentService;
import com.ossez.wechat.oa.api.test.ApiTestModule;
import com.ossez.wechat.oa.bean.comment.WxMpCommentListVo;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Test Classes move to use Junit 5
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpCommentServiceImplTest {
    @Inject
    private WeChatOfficialAccountService wxService;

    @Test
    public void testOpen() throws WxErrorException {
        this.wxService.getCommentService().open("1", null);
        this.wxService.getCommentService().open("1", 0);
    }

    @Test
    public void testClose() throws WxErrorException {
        this.wxService.getCommentService().close("1000000001", null);
        this.wxService.getCommentService().close("1", 0);
    }

    @Test
    public void testList() throws WxErrorException {
        String expectedResponse = "{\n" +
                "    \"errcode\": 0,\n" +
                "    \"errmsg\": \"ok\",\n" +
                "    \"total\": 1,\n" +
                "    \"comment\": [\n" +
                "        {\n" +
                "            \"user_comment_id\": 1,\n" +
                "            \"openid\": \"OPENID\",\n" +
                "            \"create_time\": \"CREATE_TIME\",\n" +
                "            \"content\": \"CONTENT\",\n" +
                "            \"comment_type\": 1,\n" +
                "            \"reply\": {\n" +
                "                \"content\": \"CONTENT\",\n" +
                "                \"create_time\": \"CREATE_TIME\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        wxService = Mockito.spy(wxService);
        WxMpCommentService commentService = new WxMpCommentServiceImpl(wxService);
        Mockito.doReturn(expectedResponse).when(wxService).post(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());

        final WxMpCommentListVo commentListVo = commentService.list("1", 1, 1, 1, 1);
        assertThat(commentListVo).isNotNull();
        System.out.println(commentListVo);
        assertThat(commentListVo.getTotal()).isEqualTo(1);
        assertThat(commentListVo.getComment()).isNotEmpty();

        assertThat(commentListVo.getComment().get(0).getReply()).isNotNull();
    }

    @Test
    public void testMarkElect() throws WxErrorException {
        this.wxService.getCommentService().markElect("1000000001", null, 1L);
    }

    @Test
    public void testUnmarkElect() throws WxErrorException {
        this.wxService.getCommentService().unmarkElect("1000000001", null, 1L);
    }

    @Test
    public void testDelete() throws WxErrorException {
        this.wxService.getCommentService().delete("1000000001", null, 1L);
    }

    @Test
    public void testReplyAdd() throws WxErrorException {
        this.wxService.getCommentService().replyAdd("1000000001", null, 1L, "haha");
    }

    @Test
    public void testReplyADelete() throws WxErrorException {
        this.wxService.getCommentService().replyDelete("1000000001", null, 1L);
    }
}
