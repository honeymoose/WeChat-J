package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaQrcodeServiceImplTest {
  @Inject
  private WxMaService wxService;

  @Test
  public void testCreateQrcode() throws Exception {
    final File qrCode = this.wxService.getQrcodeService().createQrcode("111", 122);
    assertThat(qrCode).isNotNull();
  }

  @Test
  public void testCreateWxaCode() throws Exception {
    final File wxCode = this.wxService.getQrcodeService().createWxaCode("111", 122);
    assertThat(wxCode).isNotNull();
  }

  @Test
  public void testCreateWxaCodeUnlimit() throws Exception {
    final File wxCode = this.wxService.getQrcodeService().createWxaCodeUnlimit("111", null);
    assertThat(wxCode).isNotNull();
  }

  @Test
  public void testCreateQrcodeBytes() throws WxErrorException {
    final byte[] qrCode = this.wxService.getQrcodeService().createQrcodeBytes("111", 122);
    assertThat(qrCode).isNotNull();
  }

  @Test
  public void testCreateWxaCodeBytes() throws WxErrorException {
    final byte[] wxCode = this.wxService.getQrcodeService().createWxaCodeBytes("111", null, 122, true, null, false);
    assertThat(wxCode).isNotNull();
  }

  @Test
  public void testCreateWxaCodeUnlimitBytes() throws WxErrorException {
    final byte[] wxCode = this.wxService.getQrcodeService().createWxaCodeUnlimitBytes("111", "pages/unknown", false, "trial", 122, true, null, false);
    assertThat(wxCode).isNotNull();
  }

  @Test
  public void testCreateQrcodeByFile() throws WxErrorException {
    final File qrCode = this.wxService.getQrcodeService().createQrcode("111", "/opt/logs");
    assertThat(qrCode).isNotNull();
  }

  @Test
  public void testCreateWxaCodeByFile() throws WxErrorException {
    final File wxCode = this.wxService.getQrcodeService().createWxaCode("111", "/opt/logs");
    assertThat(wxCode).isNotNull();
  }

  @Test
  public void testCreateQrcodeUnlimitByFile() throws WxErrorException {
    final File wxCode = this.wxService.getQrcodeService().createWxaCodeUnlimit("111", null, "/opt/logs");
    assertThat(wxCode).isNotNull();
  }
}
