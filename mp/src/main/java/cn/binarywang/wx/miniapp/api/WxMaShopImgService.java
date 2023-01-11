package cn.binarywang.wx.miniapp.api;

import com.ossez.wechat.common.bean.result.WxMinishopImageUploadCustomizeResult;
import com.ossez.wechat.common.exception.WxErrorException;

import java.io.File;

/**
 * 小程序交易组件-接入商品前必需接口
 *
 * @author liming1019
 */
public interface WxMaShopImgService {
  /**
   * 上传图片
   *
   * @param file
   * @return WxMinishopImageUploadCustomizeResult
   * @throws WxErrorException
   */
  WxMinishopImageUploadCustomizeResult uploadImg(File file) throws WxErrorException;

  /**
   * 上传图片,带respType参数
   *
   * @param file
   * @param respType
   * @return WxMinishopImageUploadCustomizeResult
   * @throws WxErrorException
   */
  WxMinishopImageUploadCustomizeResult uploadImg(File file, String respType) throws WxErrorException;

  /**
   * 上传图片链接,带respType参数
   *
   * @param imgUrl
   * @param respType
   * @return WxMinishopImageUploadCustomizeResult
   * @throws WxErrorException
   */
  WxMinishopImageUploadCustomizeResult uploadImg(String imgUrl, String respType) throws WxErrorException;
}
