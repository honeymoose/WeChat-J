package com.ossez.wechat.common.exception;

import com.ossez.wechat.common.enums.WxType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class WxErrorTest {
  @Test
  public void testFromJson() {
    String json = "{ \"errcode\": 40003, \"errmsg\": \"invalid openid\" }";
    WxError wxError = WxError.fromJson(json, WxType.MP);
    assertThat(40003).isEqualTo(wxError.getErrorCode());
//    assertEquals(wxError.getErrorMsgEn(), "invalid openid");

  }

//  @Test
//  public void testFromBadJson1() {
//    String json = "{ \"errcode\": 40003, \"errmsg\": \"invalid openid\", \"media_id\": \"12323423dsfafsf232f\" }";
//    WxError wxError = WxError.fromJson(json, WxType.MP);
//    assertEquals(40003, wxError.getErrorCode());
//    assertEquals(wxError.getErrorMsgEn(), "invalid openid");
//
//  }
//
//  @Test
//  public void testFromBadJson2() {
//    String json = "{\"access_token\":\"ACCESS_TOKEN\",\"expires_in\":7200}";
//    WxError wxError = WxError.fromJson(json, WxType.MP);
//    assertEquals(0, wxError.getErrorCode());
//    assertNull(wxError.getErrorMsg());
//
//  }

}
