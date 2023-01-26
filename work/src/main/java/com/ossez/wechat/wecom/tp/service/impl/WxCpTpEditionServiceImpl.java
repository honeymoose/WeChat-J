package com.ossez.wechat.wecom.tp.service.impl;

import com.google.gson.JsonObject;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.wecom.constant.WxCpApiPathConsts;
import lombok.RequiredArgsConstructor;
import com.ossez.wechat.wecom.bean.WxCpTpProlongTryResult;
import com.ossez.wechat.wecom.tp.service.WxCpTpEditionService;
import com.ossez.wechat.wecom.tp.service.WxCpTpService;

/**
 * 应用版本付费版本相关接口实现
 *
 * @author leigouqing  created on  2022年4月24日
 */
@RequiredArgsConstructor
public class WxCpTpEditionServiceImpl implements WxCpTpEditionService {

  /**
   * The Main service.
   */
  private final WxCpTpService mainService;

  /**
   * 延长试用期
   * <p>
   * <a href='https://developer.work.weixin.qq.com/document/path/91913'>文档地址</a>
   * <p/>
   * <ul>
   *     <li>一个应用可以多次延长试用，但是试用总天数不能超过60天</li>
   *     <li>仅限时试用或试用过期状态下的应用可以延长试用期</li>
   * </ul>
   *
   * @param buyerCorpId 购买方corpId
   * @param prolongDays 延长天数
   * @param appId       仅旧套件需要填此参数
   * @return the order
   * @throws WxErrorException the wx error exception
   */
  @Override
  public WxCpTpProlongTryResult prolongTry(String buyerCorpId, Integer prolongDays, String appId) throws WxErrorException {
    String url = mainService.getWxCpTpConfigStorage().getApiUrl(WxCpApiPathConsts.Tp.PROLONG_TRY);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("buyer_corpid", buyerCorpId);
    jsonObject.addProperty("prolong_days", prolongDays);
    jsonObject.addProperty("appid", appId);
    String result = mainService.post(url, jsonObject.toString());
    return WxCpTpProlongTryResult.fromJson(result);
  }
}
