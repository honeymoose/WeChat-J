package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import cn.binarywang.wx.miniapp.test.TestConstants;
import com.ossez.wechat.common.bean.ocr.*;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.fs.FileUtils;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2020-07-05
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaOcrServiceImplTest {
  @Inject
  private WxMaService service;

  @Test
  public void testIdCard() throws WxErrorException {
    final WxOcrIdCardResult result = this.service.getOcrService().idCard(
      "https://res.wx.qq.com/op_res/E_oqdHqP4ETOJsT46sQnXz1HbeHOpqDQTuhkYeaLaJTf-JKld7de3091dwxCQwa6");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testIdCard2() throws Exception {
    InputStream inputStream = this.getImageStream("https://res.wx.qq.com/op_res/E_oqdHqP4ETOJsT46sQnXz1HbeHOpqDQTuhkYeaLaJTf-JKld7de3091dwxCQwa6");
    File tempFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), TestConstants.FILE_JPG);
    final WxOcrIdCardResult result = this.service.getOcrService().idCard(tempFile);
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testBankCard() throws WxErrorException {
    final WxOcrBankCardResult result = this.service.getOcrService().bankCard("https://res.wx.qq.com/op_res/eP7PObYbBJj-_19EbGBL4PWe_zQ1NwET5NXSugjEWc-4ayns4Q-HFJrp-AOog8ih");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testBankCard2() throws Exception {
    InputStream inputStream = this.getImageStream("https://res.wx.qq.com/op_res/eP7PObYbBJj-_19EbGBL4PWe_zQ1NwET5NXSugjEWc-4ayns4Q-HFJrp-AOog8ih");
    File tempFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), TestConstants.FILE_JPG);
    final WxOcrBankCardResult result = this.service.getOcrService().bankCard(tempFile);
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testDriving() throws WxErrorException {
    final WxOcrDrivingResult result = this.service.getOcrService().driving("https://res.wx.qq.com/op_res/T051P5uWvh9gSJ9j78tWib53WiNi2pHSSZhoO8wnY3Av-djpsA4kA9whbtt6_Tb6");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testDriving2() throws Exception {
    InputStream inputStream = ClassLoader.getSystemResourceAsStream("mm.jpeg");
    File tempFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), TestConstants.FILE_JPG);
    final WxOcrDrivingResult result = this.service.getOcrService().driving(tempFile);
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testDrivingLicense() throws WxErrorException {
    final WxOcrDrivingLicenseResult result = this.service.getOcrService().drivingLicense("https://res.wx.qq.com/op_res/kD4YXjYVAW1eaQqn9uTA0rrOFoZRvVINitNDSGo5gJ7SzTCezNq_ZDDmU1I08kGn");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testDrivingLicense2() throws Exception {
    InputStream inputStream = this.getImageStream("https://res.wx.qq.com/op_res/kD4YXjYVAW1eaQqn9uTA0rrOFoZRvVINitNDSGo5gJ7SzTCezNq_ZDDmU1I08kGn");
    File tempFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), TestConstants.FILE_JPG);
    final WxOcrDrivingLicenseResult result = this.service.getOcrService().drivingLicense(tempFile);
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testBizLicense() throws WxErrorException {
    final WxOcrBizLicenseResult result = this.service.getOcrService().bizLicense("https://res.wx.qq.com/op_res/apCy0YbnEdjYsa_cjW6x3FlpCc20uQ-2BYE7aXnFsrB-ALHZNgdKXhzIUcrRnDoL");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testBizLicense2() throws Exception {
    InputStream inputStream = ClassLoader.getSystemResourceAsStream("mm.jpeg");
    File tempFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), TestConstants.FILE_JPG);
    final WxOcrBizLicenseResult result = this.service.getOcrService().bizLicense(tempFile);
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testComm() throws WxErrorException {
    final WxOcrCommResult result = this.service.getOcrService().comm("https://res.wx.qq.com/op_res/apCy0YbnEdjYsa_cjW6x3FlpCc20uQ-2BYE7aXnFsrB-ALHZNgdKXhzIUcrRnDoL");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testComm2() throws Exception {
    InputStream inputStream = ClassLoader.getSystemResourceAsStream("mm.jpeg");
    File tempFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), TestConstants.FILE_JPG);
    final WxOcrCommResult result = this.service.getOcrService().comm(tempFile);
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  private InputStream getImageStream(String url) {
    try {
      HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
      connection.setReadTimeout(5000);
      connection.setConnectTimeout(5000);
      connection.setRequestMethod("GET");
      if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
        return connection.getInputStream();
      }
    } catch (IOException e) {
      System.out.println("???????????????????????????????????????????????????" + url);
    }
    return null;
  }

  public static class MockTest {
    private final WxMaService wxService = mock(WxMaService.class);

    @Test
    public void testIdCard() throws Exception {
      String returnJson = "{\"type\":\"Back\",\"name\":\"??????\",\"id\":\"110101199909090099\",\"valid_date\":\"20110101-20210201\"}";

      when(wxService.get(anyString(), anyString())).thenReturn(returnJson);
      final WxMaOcrServiceImpl wxMpOcrService = new WxMaOcrServiceImpl(wxService);

      final WxOcrIdCardResult result = wxMpOcrService.idCard("abc");
      assertThat(result).isNotNull();
      System.out.println(result);
    }

    @Test
    public void testBankCard() throws Exception {
      String returnJson = "{\"number\":\"24234234345234\"}";

      when(wxService.get(anyString(), anyString())).thenReturn(returnJson);
      final WxMaOcrServiceImpl ocrService = new WxMaOcrServiceImpl(wxService);
      final WxOcrBankCardResult result = ocrService.bankCard("abc");
      assertThat(result).isNotNull();
      System.out.println(result);
    }

    @Test
    public void testDriving() throws Exception {
      String returnJson = "{\n" +
        "    \"errcode\": 0,\n" +
        "    \"errmsg\": \"ok\",\n" +
        "    \"plate_num\": \"???xxxxx\", //????????????\n" +
        "    \"vehicle_type\": \"??????????????????\", //????????????\n" +
        "    \"owner\": \"?????????xxxxx?????????\", //?????????\n" +
        "    \"addr\": \"??????????????????xxxxx???\", //??????\n" +
        "    \"use_character\": \"?????????\", //????????????\n" +
        "    \"model\": \"?????????HFCxxxxxxx\", //????????????\n" +
        "    \"vin\": \"LJ166xxxxxxxx51\", //??????????????????\n" +
        "    \"engine_num\": \"J3xxxxx3\", //???????????????\n" +
        "    \"register_date\": \"2018-07-06\", //????????????\n" +
        "    \"issue_date\": \"2018-07-01\", //????????????\n" +
        "    \"plate_num_b\": \"???xxxxx\", //????????????\n" +
        "    \"record\": \"441xxxxxx3\", //??????\n" +
        "    \"passengers_num\": \"7???\", //???????????????\n" +
        "    \"total_quality\": \"2700kg\", //?????????\n" +
        "    \"prepare_quality\": \"1995kg\", //????????????\n" +
        "    \"overall_size\": \"4582x1795x1458mm\", //????????????\n" +
        "    \"card_position_front\": {//?????????????????????????????????????????????????????????\n" +
        "        \"pos\": {\n" +
        "            \"left_top\": {\n" +
        "                \"x\": 119, \n" +
        "                \"y\": 2925\n" +
        "            }, \n" +
        "            \"right_top\": {\n" +
        "                \"x\": 1435, \n" +
        "                \"y\": 2887\n" +
        "            }, \n" +
        "            \"right_bottom\": {\n" +
        "                \"x\": 1435, \n" +
        "                \"y\": 3793\n" +
        "            }, \n" +
        "            \"left_bottom\": {\n" +
        "                \"x\": 119, \n" +
        "                \"y\": 3831\n" +
        "            }\n" +
        "        }\n" +
        "    }, \n" +
        "    \"card_position_back\": {//?????????????????????????????????????????????????????????\n" +
        "        \"pos\": {\n" +
        "            \"left_top\": {\n" +
        "                \"x\": 1523, \n" +
        "                \"y\": 2849\n" +
        "            }, \n" +
        "            \"right_top\": {\n" +
        "                \"x\": 2898, \n" +
        "                \"y\": 2887\n" +
        "            }, \n" +
        "            \"right_bottom\": {\n" +
        "                \"x\": 2927, \n" +
        "                \"y\": 3831\n" +
        "            }, \n" +
        "            \"left_bottom\": {\n" +
        "                \"x\": 1523, \n" +
        "                \"y\": 3831\n" +
        "            }\n" +
        "        }\n" +
        "    }, \n" +
        "    \"img_size\": {//????????????\n" +
        "        \"w\": 3120, \n" +
        "        \"h\": 4208\n" +
        "    }\n" +
        "}";

      when(wxService.get(anyString(), anyString())).thenReturn(returnJson);
      final WxMaOcrServiceImpl ocrService = new WxMaOcrServiceImpl(wxService);

      final WxOcrDrivingResult result = ocrService.driving("abc");
      assertThat(result).isNotNull();
      System.out.println(result);
    }

    @Test
    public void testDrivingLicense() throws Exception {
      String returnJson = "{\n" +
        "    \"errcode\": 0,\n" +
        "    \"errmsg\": \"ok\",\n" +
        "    \"id_num\": \"660601xxxxxxxx1234\", //??????\n" +
        "    \"name\": \"??????\", //??????\n" +
        "    \"sex\": \"???\", //??????\n" +
        "    \"nationality\": \"??????\", //??????\n" +
        "    \"address\": \"??????????????????xxxxx???\", //??????\n" +
        "    \"birth_date\": \"1990-12-21\", //????????????\n" +
        "    \"issue_date\": \"2012-12-21\", //??????????????????\n" +
        "    \"car_class\": \"C1\", //????????????\n" +
        "    \"valid_from\": \"2018-07-06\", //?????????????????????\n" +
        "    \"valid_to\": \"2020-07-01\", //?????????????????????\n" +
        "    \"official_seal\": \"xx?????????????????????????????????\" //????????????\n" +
        "}";
      when(wxService.get(anyString(), anyString())).thenReturn(returnJson);
      final WxMaOcrServiceImpl wxMpOcrService = new WxMaOcrServiceImpl(wxService);

      final WxOcrDrivingLicenseResult result = wxMpOcrService.drivingLicense("abc");
      assertThat(result).isNotNull();
      System.out.println(result);
    }

    @Test
    public void testBizLicense() throws Exception {
      String returnJson = "{\n" +
        "    \"errcode\": 0, \n" +
        "    \"errmsg\": \"ok\", \n" +
        "    \"reg_num\": \"123123\",//?????????\n" +
        "    \"serial\": \"123123\",//??????\n" +
        "    \"legal_representative\": \"??????\", //?????????????????????\n" +
        "    \"enterprise_name\": \"XX?????????\", //????????????\n" +
        "    \"type_of_organization\": \"????????????\", //????????????\n" +
        "    \"address\": \"XX???XX???XX???XX???\", //????????????/????????????\n" +
        "    \"type_of_enterprise\": \"xxx\", //????????????\n" +
        "    \"business_scope\": \"????????????(?????????????????????????????????????????????????????????)???\", //????????????\n" +
        "    \"registered_capital\": \"200???\", //????????????\n" +
        "    \"paid_in_capital\": \"200???\", //????????????\n" +
        "    \"valid_period\": \"2019???1???1???\", //????????????\n" +
        "    \"registered_date\": \"2018???1???1???\", //????????????/????????????\n" +
        "    \"cert_position\": { //??????????????????\n" +
        "        \"pos\": {\n" +
        "            \"left_top\": {\n" +
        "                \"x\": 155, \n" +
        "                \"y\": 191\n" +
        "            }, \n" +
        "            \"right_top\": {\n" +
        "                \"x\": 725, \n" +
        "                \"y\": 157\n" +
        "            }, \n" +
        "            \"right_bottom\": {\n" +
        "                \"x\": 743, \n" +
        "                \"y\": 512\n" +
        "            }, \n" +
        "            \"left_bottom\": {\n" +
        "                \"x\": 164, \n" +
        "                \"y\": 525\n" +
        "            }\n" +
        "        }\n" +
        "    }, \n" +
        "    \"img_size\": { //????????????\n" +
        "        \"w\": 966, \n" +
        "        \"h\": 728\n" +
        "    }\n" +
        "}";
      when(wxService.get(anyString(), anyString())).thenReturn(returnJson);
      final WxMaOcrServiceImpl ocrService = new WxMaOcrServiceImpl(wxService);

      final WxOcrBizLicenseResult result = ocrService.bizLicense("abc");
      assertThat(result).isNotNull();
      System.out.println(result);
    }

    @Test
    public void testComm() throws Exception {
      String returnJson = "{\n" +
        "    \"errcode\": 0, \n" +
        "    \"errmsg\": \"ok\", \n" +
        "    \"items\": [ //????????????\n" +
        "        {\n" +
        "            \"text\": \"??????\", \n" +
        "            \"pos\": {\n" +
        "                \"left_top\": {\n" +
        "                    \"x\": 575, \n" +
        "                    \"y\": 519\n" +
        "                }, \n" +
        "                \"right_top\": {\n" +
        "                    \"x\": 744, \n" +
        "                    \"y\": 519\n" +
        "                }, \n" +
        "                \"right_bottom\": {\n" +
        "                    \"x\": 744, \n" +
        "                    \"y\": 532\n" +
        "                }, \n" +
        "                \"left_bottom\": {\n" +
        "                    \"x\": 573, \n" +
        "                    \"y\": 532\n" +
        "                }\n" +
        "            }\n" +
        "        }, \n" +
        "        {\n" +
        "            \"text\": \"????????????\", \n" +
        "            \"pos\": {\n" +
        "                \"left_top\": {\n" +
        "                    \"x\": 670, \n" +
        "                    \"y\": 516\n" +
        "                }, \n" +
        "                \"right_top\": {\n" +
        "                    \"x\": 762, \n" +
        "                    \"y\": 517\n" +
        "                }, \n" +
        "                \"right_bottom\": {\n" +
        "                    \"x\": 762, \n" +
        "                    \"y\": 532\n" +
        "                }, \n" +
        "                \"left_bottom\": {\n" +
        "                    \"x\": 670, \n" +
        "                    \"y\": 531\n" +
        "                }\n" +
        "            }\n" +
        "        }\n" +
        "    ], \n" +
        "    \"img_size\": { //????????????\n" +
        "        \"w\": 1280, \n" +
        "        \"h\": 720\n" +
        "    }\n" +
        "}";
      when(wxService.get(anyString(), anyString())).thenReturn(returnJson);
      final WxMaOcrServiceImpl ocrService = new WxMaOcrServiceImpl(wxService);

      final WxOcrCommResult result = ocrService.comm("abc");
      assertThat(result).isNotNull();
      System.out.println(result);
    }
  }
}
