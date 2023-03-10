package com.ossez.wechat.wecom.bean.external.msg;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.constant.WxCpConsts;
import lombok.Data;

import java.io.Serializable;

/**
 * The type Attachment.
 *
 * @author chutian0124
 */
@Data
public class Attachment implements Serializable {
  private static final long serialVersionUID = -8078748379570640198L;

  @SerializedName("msgtype")
  private String msgType;

  private Image image;

  private Link link;

  @SerializedName("miniprogram")
  private MiniProgram miniProgram;

  private Video video;

  private File file;

  /**
   * Sets image.
   *
   * @param image the image
   */
  public void setImage(Image image) {
    this.image = image;
    this.msgType = WxCpConsts.WelcomeMsgType.IMAGE;
  }

  /**
   * Sets link.
   *
   * @param link the link
   */
  public void setLink(Link link) {
    this.link = link;
    this.msgType = WxCpConsts.WelcomeMsgType.LINK;
  }

  /**
   * Sets mini program.
   *
   * @param miniProgram the mini program
   */
  public void setMiniProgram(MiniProgram miniProgram) {
    this.miniProgram = miniProgram;
    this.msgType = WxCpConsts.WelcomeMsgType.MINIPROGRAM;
  }

  /**
   * Sets video.
   *
   * @param video the video
   */
  public void setVideo(Video video) {
    this.video = video;
    this.msgType = WxCpConsts.WelcomeMsgType.VIDEO;
  }

  /**
   * Sets file.
   *
   * @param file the file
   */
  public void setFile(File file) {
    this.file = file;
    this.msgType = WxCpConsts.WelcomeMsgType.FILE;
  }
}
