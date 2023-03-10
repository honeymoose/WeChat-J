package com.ossez.wechat.oa.demo;

import com.ossez.wechat.common.config.ConfigStorage;
import com.ossez.wechat.oa.api.WxMpMessageRouter;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.bean.message.WxMpXmlMessage;
import com.ossez.wechat.oa.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Daniel Qian
 */
public class WxMpEndpointServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected ConfigStorage configStorage;
  protected WeChatOfficialAccountService weChatOfficialAccountService;
  protected WxMpMessageRouter wxMpMessageRouter;

  public WxMpEndpointServlet(ConfigStorage configStorage, WeChatOfficialAccountService weChatOfficialAccountService,
                             WxMpMessageRouter wxMpMessageRouter) {
    this.configStorage = configStorage;
    this.weChatOfficialAccountService = weChatOfficialAccountService;
    this.wxMpMessageRouter = wxMpMessageRouter;
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
    throws IOException {

    response.setContentType("text/html;charset=utf-8");
    response.setStatus(HttpServletResponse.SC_OK);

    String signature = request.getParameter("signature");
    String nonce = request.getParameter("nonce");
    String timestamp = request.getParameter("timestamp");

    if (!this.weChatOfficialAccountService.checkSignature(timestamp, nonce, signature)) {
      // 消息签名不正确，说明不是公众平台发过来的消息
      response.getWriter().println("非法请求");
      return;
    }

    String echostr = request.getParameter("echostr");
    if (StringUtils.isNotBlank(echostr)) {
      // 说明是一个仅仅用来验证的请求，回显echostr
      response.getWriter().println(echostr);
      return;
    }

    String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ?
      "raw" :
      request.getParameter("encrypt_type");

    if ("raw".equals(encryptType)) {
      // 明文传输的消息
      WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
      WxMpXmlOutMessage outMessage = this.wxMpMessageRouter.route(inMessage);
      if (outMessage != null) {
        response.getWriter().write(outMessage.toXml());
      }
      return;
    }

    if ("aes".equals(encryptType)) {
      // 是aes加密的消息
      String msgSignature = request.getParameter("msg_signature");
      WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), this.configStorage, timestamp, nonce, msgSignature);
      WxMpXmlOutMessage outMessage = this.wxMpMessageRouter.route(inMessage);
      response.getWriter().write(outMessage.toEncryptedXml(this.configStorage));
      return;
    }

    response.getWriter().println("不可识别的加密类型");
    return;
  }

}
