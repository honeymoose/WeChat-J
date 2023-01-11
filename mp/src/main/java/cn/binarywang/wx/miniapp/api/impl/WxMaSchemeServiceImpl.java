package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaSchemeService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.scheme.WxMaGenerateSchemeRequest;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.json.GsonParser;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Scheme.GENERATE_SCHEME_URL;

/**
 * @author : cofedream
 * created on  : 2021-01-28
 */
@AllArgsConstructor
public class WxMaSchemeServiceImpl implements WxMaSchemeService {
  private static final String ERR_CODE = "errcode";
  private final WxMaService wxMaService;

  @Override
  public String generate(WxMaGenerateSchemeRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(GENERATE_SCHEME_URL, request.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return jsonObject.get("openlink").getAsString();
  }
}
