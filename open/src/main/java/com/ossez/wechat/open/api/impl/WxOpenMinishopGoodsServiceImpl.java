package com.ossez.wechat.open.api.impl;

import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import com.ossez.wechat.open.bean.minishopGoods.AddMinishopGoodsSPU;
import com.ossez.wechat.open.bean.minishopGoods.GoodsCatList;
import com.ossez.wechat.open.bean.minishopGoods.ParentCatId;
import com.ossez.wechat.open.bean.result.WxOpenResult;
import lombok.extern.slf4j.Slf4j;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.open.api.WxOpenMinishopGoodsService;

@Slf4j
public class WxOpenMinishopGoodsServiceImpl extends WxMaServiceImpl implements WxOpenMinishopGoodsService {

  @Override
  public GoodsCatList getMinishopGoodsCat(ParentCatId dto) throws WxErrorException {
    String response = post(getMinishopGoodsCatUrl, dto.toJsonObject().toString());
    log.info(response);
    return null;
  }

  @Override
  public WxOpenResult addMinishopGoodsSPU(AddMinishopGoodsSPU dto) throws WxErrorException {
    String response = post(addMinishopGoodsSPUUrl, dto.toJsonObject().toString());
    return null;
  }








}
