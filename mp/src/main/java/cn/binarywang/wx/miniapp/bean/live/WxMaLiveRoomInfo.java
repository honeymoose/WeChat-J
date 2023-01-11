package cn.binarywang.wx.miniapp.bean.live;

import lombok.Data;

import java.io.Serializable;

/**
 * 直播间信息
 *
 * @author unkown
 */
@Data
public class WxMaLiveRoomInfo implements Serializable {
  private static final long serialVersionUID = 7745775280267417154L;

  /**
   * 直播间ID
   */
  private Integer id;
  /**
   * 直播间名字，最短3个汉字，最长17个汉字，1个汉字相当于2个字符
   **/
  private String name;
  /**
   * 背景图，填入mediaID（mediaID获取后，三天内有效）；图片mediaID的获取，
   * 请参考以下文档： https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/New_temporary_materials.html；
   * 直播间背景图，图片规则：建议像素1080*1920，大小不超过2M
   **/
  private String coverImg;
  /**
   * 直播计划开始时间（开播时间需要在当前时间的10分钟后 并且 开始时间不能在 6 个月后）
   **/
  private Long startTime;
  /**
   * 直播计划结束时间（开播时间和结束时间间隔不得短于30分钟，不得超过24小时）
   **/
  private Long endTime;
  /**
   * 主播昵称，最短2个汉字，最长15个汉字，1个汉字相当于2个字符
   **/
  private String anchorName;
  /**
   * 主播微信号，如果未实名认证，需要先前往“小程序直播”小程序进行实名验证,
   * 小程序二维码链接：https://res.wx.qq.com/op_res/BbVNeczA1XudfjVqCVoKgfuWe7e3aUhokktRVOqf_F0IqS6kYR--atCpVNUUC3zr
   **/
  private String anchorWechat;
  /**
   * 主播副号微信号，如果未实名认证，需要先前往“小程序直播”小程序进行实名验证,
   * 小程序二维码链接：https://res.wx.qq.com/op_res/BbVNeczA1XudfjVqCVoKgfuWe7e3aUhokktRVOqf_F0IqS6kYR--atCpVNUUC3zr
   **/
  private String subAnchorWechat;
  /**
   * 创建者微信号，不传入则此直播间所有成员可见。传入则此房间仅创建者、管理员、超管、直播间主播可见
   **/
  private String createrWechat;
  /**
   * 分享图，填入mediaID（mediaID获取后，三天内有效）；
   * 图片mediaID的获取，请参考以下文档：
   * https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/New_temporary_materials.html；
   * 直播间分享图，图片规则：建议像素800*640，大小不超过1M；
   **/
  private String shareImg;
  /**
   * 购物直播频道封面图，填入mediaID（mediaID获取后，三天内有效）；
   * 图片mediaID的获取，请参考以下文档：
   * https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/New_temporary_materials.html;
   * 购物直播频道封面图，图片规则：建议像素800*800，大小不超过100KB；
   * <p>
   * 该字段仅用于
   * <a href="https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/Mini_Programs/live_player/studio-api.html#6">编辑直播间</a>，不用于创建直播间
   * </p>
   **/
  private String feedsImg;
  /**
   * 直播间分享图，填入mediaID（mediaID获取后，三天内有效）；图片规则：建议像素1080*1920，大小不超过2M；
   * 图片mediaID的获取，请参考以下文档：
   * https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/New_temporary_materials.html；
   * <p>
   * 该字段仅用于
   * <a href="https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/Mini_Programs/live_player/studio-api.html#1">创建直播间</a>，不用于编辑直播间
   * </p>
   **/
  private String anchorImg;
  /**
   * 是否开启官方收录 【1: 开启，0：关闭】，默认开启收录
   **/
  private Integer isFeedsPublic;
  /**
   * 直播间类型 【1: 推流，0：手机直播】
   **/
  private Integer type;
  /**
   * 横屏、竖屏 【1：横屏，0：竖屏】（横屏：视频宽高比为16:9、4:3、1.85:1 ；竖屏：视频宽高比为9:16、2:3）
   **/
  private Integer screenType;
  /**
   * 是否关闭点赞 【0：开启，1：关闭】（若关闭，直播开始后不允许开启）
   **/
  private Integer closeLike;
  /**
   * 是否关闭货架 【0：开启，1：关闭】（若关闭，直播开始后不允许开启）
   **/
  private Integer closeGoods;
  /**
   * 是否关闭评论 【0：开启，1：关闭】（若关闭，直播开始后不允许开启）
   **/
  private Integer closeComment;
  /**
   * 是否关闭回放 【0：开启，1：关闭】默认关闭回放
   **/
  private Integer closeReplay;
  /**
   * 是否关闭分享 【0：开启，1：关闭】默认开启分享（直播开始后不允许修改）
   **/
  private Integer closeShare;
  /**
   * closeKf	Number	否	是否关闭客服 【0：开启，1：关闭】 默认关闭客服
   **/
  private Integer closeKf;
}
