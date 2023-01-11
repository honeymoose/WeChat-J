package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 交易组件-spu商品详情图文
 * @author <a href="https://github.com/borisbao">boris</a>
 * @since 2021-03-22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaShopSpuDescInfo implements Serializable {
  private static final long serialVersionUID = 9116393835880797275L;

  /**
   * 商品详情图文-描述
   * <pre>
   * 是否必填： 否
   * </pre>
   */
  @SerializedName("desc")
  private String desc;

  /**
   * 商品详情图片
   * <pre>
   * 是否必填： 否
   * </pre>
   */
  @SerializedName("imgs")
  private List<String> imgList;

}
