package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.*;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.common.bean.ToJson;
import com.ossez.wechat.common.model.WeChatAccessToken;
import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.exception.WxRuntimeException;
import com.ossez.wechat.common.service.WxImgProcService;
import com.ossez.wechat.common.service.WxOcrService;
import com.ossez.wechat.common.util.DataUtils;
import com.ossez.wechat.common.util.crypto.SHA1;
import com.ossez.wechat.common.util.http.RequestExecutor;
import com.ossez.wechat.common.util.http.RequestHttp;
import com.ossez.wechat.common.util.http.SimpleGetRequestExecutor;
import com.ossez.wechat.common.util.http.SimplePostRequestExecutor;
import com.ossez.wechat.common.util.json.GsonParser;
import com.ossez.wechat.common.util.json.WxGsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @see #doGetAccessTokenRequest
 */
@Slf4j
public abstract class BaseWxMaServiceImpl<H, P> implements WxMaService, RequestHttp<H, P> {
  protected static final Gson GSON = new Gson();
  private final WxMaMsgService kefuService = new WxMaMsgServiceImpl(this);
  private final WxMaMediaService materialService = new WxMaMediaServiceImpl(this);
  private final WxMaUserService userService = new WxMaUserServiceImpl(this);
  private final WxMaQrcodeService qrCodeService = new WxMaQrcodeServiceImpl(this);
  private final WxMaSchemeService schemeService = new WxMaSchemeServiceImpl(this);
  private final WxMaAnalysisService analysisService = new WxMaAnalysisServiceImpl(this);
  private final WxMaCodeService codeService = new WxMaCodeServiceImpl(this);
  private final WxMaInternetService internetService = new WxMaInternetServiceImpl(this);
  private final WxMaSettingService settingService = new WxMaSettingServiceImpl(this);
  private final WxMaJsapiService jsapiService = new WxMaJsapiServiceImpl(this);
  private final WxMaShareService shareService = new WxMaShareServiceImpl(this);
  private final WxMaRunService runService = new WxMaRunServiceImpl(this);
  private final WxMaSecCheckService secCheckService = new WxMaSecCheckServiceImpl(this);
  private final WxMaPluginService pluginService = new WxMaPluginServiceImpl(this);
  private final WxMaExpressService expressService = new WxMaExpressServiceImpl(this);
  private final WxMaSubscribeService subscribeService = new WxMaSubscribeServiceImpl(this);
  private final WxMaCloudService cloudService = new WxMaCloudServiceImpl(this);
  private final WxMaLiveService liveService = new WxMaLiveServiceImpl(this);
  private final WxMaLiveGoodsService liveGoodsService = new WxMaLiveGoodsServiceImpl(this);
  private final WxMaLiveMemberService liveMemberService = new WxMaLiveMemberServiceImpl(this);
  private final WxOcrService ocrService = new WxMaOcrServiceImpl(this);
  private final WxImgProcService imgProcService = new WxMaImgProcServiceImpl(this);
  private final WxMaShopSpuService shopSpuService = new WxMaShopSpuServiceImpl(this);
  private final WxMaShopOrderService shopOrderService = new WxMaShopOrderServiceImpl(this);
  private final WxMaShopRegisterService shopRegisterService = new WxMaShopRegisterServiceImpl(this);
  private final WxMaShopAccountService shopAccountService = new WxMaShopAccountServiceImpl(this);
  private final WxMaShopCatService shopCatService = new WxMaShopCatServiceImpl(this);
  private final WxMaShopImgService shopImgService = new WxMaShopImgServiceImpl(this);
  private final WxMaShopAuditService shopAuditService = new WxMaShopAuditServiceImpl(this);
  private final WxMaShopAfterSaleService shopAfterSaleService = new WxMaShopAfterSaleServiceImpl(this);
  private final WxMaShopDeliveryService shopDeliveryService = new WxMaShopDeliveryServiceImpl(this);
  private final WxMaLinkService linkService = new WxMaLinkServiceImpl(this);
  private final WxMaReimburseInvoiceService reimburseInvoiceService = new WxMaReimburseInvoiceServiceImpl(this);
  private final WxMaDeviceSubscribeService deviceSubscribeService = new WxMaDeviceSubscribeServiceImpl(this);
  private final WxMaMarketingService marketingService = new WxMaMarketingServiceImpl(this);
  private final WxMaImmediateDeliveryService immediateDeliveryService = new WxMaImmediateDeliveryServiceImpl(this);
  private final WxMaSafetyRiskControlService safetyRiskControlService = new WxMaSafetyRiskControlServiceImpl(this);
  private final WxMaShopSharerService shopSharerService = new WxMaShopSharerServiceImpl(this);
  private final WxMaProductService productService = new WxMaProductServiceImpl(this);
  private final WxMaProductOrderService productOrderService = new WxMaProductOrderServiceImpl(this);
  private final WxMaShopCouponService wxMaShopCouponService = new WxMaShopCouponServiceImpl(this);
  private final WxMaShopPayService wxMaShopPayService = new WxMaShopPayServiceImpl(this);
  private Map<String, WxMaConfig> configMap;
  private int retrySleepMillis = 1000;
  private int maxRetryTimes = 5;

  @Override
  public RequestHttp getRequestHttp() {
    return this;
  }

  @Override
  public String getPaidUnionId(String openid, String transactionId, String mchId, String outTradeNo)
    throws WxErrorException {
    Map<String, String> params = new HashMap<>(8);
    params.put("openid", openid);

    if (StringUtils.isNotEmpty(transactionId)) {
      params.put("transaction_id", transactionId);
    }

    if (StringUtils.isNotEmpty(mchId)) {
      params.put("mch_id", mchId);
    }

    if (StringUtils.isNotEmpty(outTradeNo)) {
      params.put("out_trade_no", outTradeNo);
    }

    String responseContent = this.get(GET_PAID_UNION_ID_URL, Joiner.on("&").withKeyValueSeparator("=").join(params));
    WxError error = WxError.fromJson(responseContent, WxType.MiniApp);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }

    return GsonParser.parse(responseContent).get("unionid").getAsString();
  }

  @Override
  public WxMaJscode2SessionResult jsCode2SessionInfo(String jsCode) throws WxErrorException {
    final WxMaConfig config = getWxMaConfig();
    Map<String, String> params = new HashMap<>(8);
    params.put("appid", config.getAppid());
    params.put("secret", config.getSecret());
    params.put("js_code", jsCode);
    params.put("grant_type", "authorization_code");

    String result = get(JSCODE_TO_SESSION_URL, Joiner.on("&").withKeyValueSeparator("=").join(params));
    return WxMaJscode2SessionResult.fromJson(result);
  }

  @Override
  public void setDynamicData(int lifespan, String type, int scene, String data) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("lifespan", lifespan);
    jsonObject.addProperty("query", WxGsonBuilder.create().toJson(ImmutableMap.of("type", type)));
    jsonObject.addProperty("data", data);
    jsonObject.addProperty("scene", scene);

    this.post(SET_DYNAMIC_DATA_URL, jsonObject.toString());
  }

  @Override
  public boolean checkSignature(String timestamp, String nonce, String signature) {
    try {
      return SHA1.gen(this.getWxMaConfig().getToken(), timestamp, nonce).equals(signature);
    } catch (Exception e) {
      log.error("Checking signature failed, and the reason is :" + e.getMessage());
      return false;
    }
  }

  @Override
  public String getAccessToken() throws WxErrorException {
    return getAccessToken(false);
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    if (!forceRefresh && !this.getWxMaConfig().isAccessTokenExpired()) {
      return this.getWxMaConfig().getAccessToken();
    }

    Lock lock = this.getWxMaConfig().getAccessTokenLock();
    boolean locked = false;
    try {
      do {
        locked = lock.tryLock(100, TimeUnit.MILLISECONDS);
        if (!forceRefresh && !this.getWxMaConfig().isAccessTokenExpired()) {
          return this.getWxMaConfig().getAccessToken();
        }
      } while (!locked);
      String response = doGetAccessTokenRequest();
      return extractAccessToken(response);
    } catch (IOException | InterruptedException e) {
      throw new WxRuntimeException(e);
    } finally {
      if (locked) {
        lock.unlock();
      }
    }
  }

  /**
   * ????????????????????????AccessToken
   *
   * @return .
   * @throws IOException .
   */
  protected abstract String doGetAccessTokenRequest() throws IOException;

  @Override
  public String get(String url, String queryParam) throws WxErrorException {
    return execute(SimpleGetRequestExecutor.create(this), url, queryParam);
  }

  @Override
  public String post(String url, String postData) throws WxErrorException {
    return execute(SimplePostRequestExecutor.create(this), url, postData);
  }

  @Override
  public String post(String url, Object obj) throws WxErrorException {
    return this.execute(SimplePostRequestExecutor.create(this), url, WxGsonBuilder.create().toJson(obj));
  }

  @Override
  public String post(String url, ToJson obj) throws WxErrorException {
    return this.post(url, obj.toJson());
  }

  @Override
  public String post(String url, JsonObject jsonObject) throws WxErrorException {
    return this.post(url, jsonObject.toString());
  }

  /**
   * ???????????????????????????????????????????????????????????????access_token??????????????????????????????????????????????????????????????????????????????
   */
  @Override
  public <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
    int retryTimes = 0;
    do {
      try {
        return this.executeInternal(executor, uri, data, false);
      } catch (WxErrorException e) {
        if (retryTimes + 1 > this.maxRetryTimes) {
          log.warn("???????????????????????????{}???", maxRetryTimes);
          //???????????????????????????????????????????????????????????????
          throw new WxErrorException(WxError.builder()
            .errorCode(e.getError().getErrorCode())
            .errorMsg("?????????????????????????????????????????????")
            .build());
        }

        WxError error = e.getError();
        // -1 ????????????, 1000ms?????????
        if (error.getErrorCode() == -1) {
          int sleepMillis = this.retrySleepMillis * (1 << retryTimes);
          try {
            log.warn("?????????????????????{} ms ?????????(???{}???)", sleepMillis, retryTimes + 1);
            Thread.sleep(sleepMillis);
          } catch (InterruptedException e1) {
            Thread.currentThread().interrupt();
          }
        } else {
          throw e;
        }
      }
    } while (retryTimes++ < this.maxRetryTimes);

    log.warn("???????????????????????????{}???", this.maxRetryTimes);
    throw new WxRuntimeException("??????????????????????????????????????????");
  }

  private <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, E data, boolean doNotAutoRefreshToken) throws WxErrorException {
    E dataForLog = DataUtils.handleDataWithSecret(data);

    if (uri.contains("access_token=")) {
      throw new IllegalArgumentException("uri?????????????????????access_token: " + uri);
    }
    String accessToken = getAccessToken(false);

    if (StringUtils.isNotEmpty(this.getWxMaConfig().getApiHostUrl())) {
      uri = uri.replace("https://api.weixin.qq.com", this.getWxMaConfig().getApiHostUrl());
    }

    String uriWithAccessToken = uri + (uri.contains("?") ? "&" : "?") + "access_token=" + accessToken;

    try {
      T result = executor.execute(uriWithAccessToken, data, WxType.MiniApp);
      log.debug("\n??????????????????: {}\n?????????????????????{}\n?????????????????????{}", uriWithAccessToken, dataForLog, result);
      return result;
    } catch (WxErrorException e) {
      WxError error = e.getError();
      if (WeChatConstant.ACCESS_TOKEN_ERROR_CODES.contains(error.getErrorCode())) {
        // ????????????WxMaConfig???access token???????????????????????????????????????????????????access token
        Lock lock = this.getWxMaConfig().getAccessTokenLock();
        lock.lock();
        try {
          if (StringUtils.equals(this.getWxMaConfig().getAccessToken(), accessToken)) {
            this.getWxMaConfig().expireAccessToken();
          }
        } catch (Exception ex) {
          this.getWxMaConfig().expireAccessToken();
        } finally {
          lock.unlock();
        }
        if (this.getWxMaConfig().autoRefreshToken() && !doNotAutoRefreshToken) {
          log.warn("????????????????????????access_token??????????????????{}??????????????????{}", error.getErrorCode(), error.getErrorMsg());
          //???????????????????????????
          //???????????????????????????????????????????????????,?????????????????????????????????access token,??????????????????????????????token???????????????????????????,???????????????
          return this.executeInternal(executor, uri, data, true);
        }
      }

      if (error.getErrorCode() != 0) {
        log.warn("\n??????????????????: {}\n?????????????????????{}\n?????????????????????{}", uriWithAccessToken, dataForLog, error);
        throw new WxErrorException(error, e);
      }
      return null;
    } catch (IOException e) {
      log.warn("\n??????????????????: {}\n?????????????????????{}\n?????????????????????{}", uriWithAccessToken, dataForLog, e.getMessage());
      throw new WxRuntimeException(e);
    }
  }

  /**
   * ???????????????AccessToken
   *
   * @param resultContent ????????????
   * @return access token
   * @throws WxErrorException ??????
   */
  protected String extractAccessToken(String resultContent) throws WxErrorException {
    log.info("resultContent: " + resultContent);
    WxMaConfig config = this.getWxMaConfig();
    WxError error = WxError.fromJson(resultContent, WxType.MiniApp);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    WeChatAccessToken accessToken = WeChatAccessToken.fromJson(resultContent);
    config.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
    return accessToken.getAccessToken();
  }

  @Override
  public WxMaConfig getWxMaConfig() {
    if (this.configMap.size() == 1) {
      // ???????????????????????????????????????????????????
      return this.configMap.values().iterator().next();
    }

    return this.configMap.get(WxMaConfigHolder.get());
  }

  @Override
  public void setWxMaConfig(WxMaConfig maConfig) {
    final String appid = maConfig.getAppid();
    this.setMultiConfigs(ImmutableMap.of(appid, maConfig), appid);
  }

  @Override
  public void setMultiConfigs(Map<String, WxMaConfig> configs) {
    this.setMultiConfigs(configs, configs.keySet().iterator().next());
  }

  @Override
  public void setMultiConfigs(Map<String, WxMaConfig> configs, String defaultMiniappId) {
    this.configMap = Maps.newHashMap(configs);
    WxMaConfigHolder.set(defaultMiniappId);
    this.initHttp();
  }

  @Override
  public void addConfig(String miniappId, WxMaConfig configStorages) {
    synchronized (this) {
      if (this.configMap == null) {
        this.setWxMaConfig(configStorages);
      } else {
        WxMaConfigHolder.set(miniappId);
        this.configMap.put(miniappId, configStorages);
      }
    }
  }

  @Override
  public void removeConfig(String miniappId) {
    synchronized (this) {
      if (this.configMap.size() == 1) {
        this.configMap.remove(miniappId);
        log.warn("???????????????????????????????????????{}??????????????????setWxMaConfig???setMultiConfigs????????????", miniappId);
        return;
      }
      if (WxMaConfigHolder.get().equals(miniappId)) {
        this.configMap.remove(miniappId);
        final String defaultMpId = this.configMap.keySet().iterator().next();
        WxMaConfigHolder.set(defaultMpId);
        log.warn("?????????????????????????????????????????????{}????????????????????????", defaultMpId);
        return;
      }
      this.configMap.remove(miniappId);
    }
  }

  @Override
  public WxMaService switchoverTo(String miniappId) {
    if (this.configMap.containsKey(miniappId)) {
      WxMaConfigHolder.set(miniappId);
      return this;
    }

    throw new WxRuntimeException(String.format("?????????????????????%s??????????????????????????????????????????", miniappId));
  }

  @Override
  public boolean switchover(String mpId) {
    if (this.configMap.containsKey(mpId)) {
      WxMaConfigHolder.set(mpId);
      return true;
    }

    log.error("?????????????????????{}??????????????????????????????????????????", mpId);
    return false;
  }

  @Override
  public void setRetrySleepMillis(int retrySleepMillis) {
    this.retrySleepMillis = retrySleepMillis;
  }

  @Override
  public void setMaxRetryTimes(int maxRetryTimes) {
    this.maxRetryTimes = maxRetryTimes;
  }

  @Override
  public WxMaMsgService getMsgService() {
    return this.kefuService;
  }

  @Override
  public WxMaMediaService getMediaService() {
    return this.materialService;
  }

  @Override
  public WxMaUserService getUserService() {
    return this.userService;
  }

  @Override
  public WxMaQrcodeService getQrcodeService() {
    return this.qrCodeService;
  }

  @Override
  public WxMaSchemeService getWxMaSchemeService() {
    return schemeService;
  }

  @Override
  public WxMaSubscribeService getSubscribeService() {
    return this.subscribeService;
  }

  @Override
  public WxMaAnalysisService getAnalysisService() {
    return this.analysisService;
  }

  @Override
  public WxMaCodeService getCodeService() {
    return this.codeService;
  }

  @Override
  public WxMaJsapiService getJsapiService() {
    return this.jsapiService;
  }

  @Override
  public WxMaSettingService getSettingService() {
    return this.settingService;
  }

  @Override
  public WxMaShareService getShareService() {
    return this.shareService;
  }

  @Override
  public WxMaRunService getRunService() {
    return this.runService;
  }

  @Override
  public WxMaSecCheckService getSecCheckService() {
    return this.secCheckService;
  }

  @Override
  public WxMaPluginService getPluginService() {
    return this.pluginService;
  }

  @Override
  public WxMaExpressService getExpressService() {
    return this.expressService;
  }

  @Override
  public WxMaCloudService getCloudService() {
    return this.cloudService;
  }

  @Override
  public WxMaInternetService getInternetService() {
    return this.internetService;
  }

  @Override
  public WxMaLiveService getLiveService() {
    return this.liveService;
  }

  @Override
  public WxMaLiveGoodsService getLiveGoodsService() {
    return this.liveGoodsService;
  }

  @Override
  public WxMaLiveMemberService getLiveMemberService() {
    return this.liveMemberService;
  }

  @Override
  public WxOcrService getOcrService() {
    return this.ocrService;
  }

  @Override
  public WxImgProcService getImgProcService() {
    return this.imgProcService;
  }

  @Override
  public WxMaShopSpuService getShopSpuService() {
    return this.shopSpuService;
  }

  @Override
  public WxMaShopOrderService getShopOrderService() {
    return this.shopOrderService;
  }

  @Override
  public WxMaShopRegisterService getShopRegisterService() {
    return this.shopRegisterService;
  }

  @Override
  public WxMaShopAccountService getShopAccountService() {
    return this.shopAccountService;
  }

  @Override
  public WxMaShopCatService getShopCatService() {
    return this.shopCatService;
  }

  @Override
  public WxMaShopImgService getShopImgService() {
    return this.shopImgService;
  }

  @Override
  public WxMaShopAuditService getShopAuditService() {
    return this.shopAuditService;
  }

  @Override
  public WxMaShopAfterSaleService getShopAfterSaleService() {
    return this.shopAfterSaleService;
  }

  @Override
  public WxMaShopDeliveryService getShopDeliveryService() {
    return this.shopDeliveryService;
  }

  @Override
  public WxMaLinkService getLinkService() {
    return this.linkService;
  }

  @Override
  public WxMaReimburseInvoiceService getReimburseInvoiceService() {
    return this.reimburseInvoiceService;
  }

  @Override
  public WxMaDeviceSubscribeService getDeviceSubscribeService(){ return this.deviceSubscribeService; }

  @Override
  public WxMaMarketingService getMarketingService() {return  this.marketingService;  }

  @Override
  public WxMaImmediateDeliveryService getWxMaImmediateDeliveryService() {
    return this.immediateDeliveryService;
  }

  @Override
  public WxMaSafetyRiskControlService getSafetyRiskControlService(){ return this.safetyRiskControlService; }

  @Override
  public WxMaShopSharerService getShopSharerService() {return this.shopSharerService; }

  @Override
  public WxMaProductService getProductService() { return this.productService; }

  @Override
  public WxMaProductOrderService getProductOrderService() {
    return this.productOrderService;
  }

  @Override
  public WxMaShopCouponService getWxMaShopCouponService() {
    return this.wxMaShopCouponService;
  }

  @Override
  public WxMaShopPayService getWxMaShopPayService() {
    return this.wxMaShopPayService;
  }
}
