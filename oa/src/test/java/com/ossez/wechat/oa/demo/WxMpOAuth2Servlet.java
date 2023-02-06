package com.ossez.wechat.oa.demo;

import com.ossez.wechat.common.model.entity.WeChatOAuth2UserInfo;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.common.model.WeChatOAuth2AccessToken;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WxMpOAuth2Servlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected WeChatOfficialAccountService weChatOfficialAccountService;

  public WxMpOAuth2Servlet(WeChatOfficialAccountService weChatOfficialAccountService) {
    this.weChatOfficialAccountService = weChatOfficialAccountService;
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
    throws IOException {

    response.setContentType("text/html;charset=utf-8");
    response.setStatus(HttpServletResponse.SC_OK);

    String code = request.getParameter("code");
    try {
      response.getWriter().println("<h1>code</h1>");
      response.getWriter().println(code);

      WeChatOAuth2AccessToken oAuth2AccessToken = this.weChatOfficialAccountService.getOAuth2Service().getAccessToken(code);
      response.getWriter().println("<h1>access token</h1>");
      response.getWriter().println(oAuth2AccessToken.toString());

      WeChatOAuth2UserInfo wxMpUser = this.weChatOfficialAccountService.getOAuth2Service().getUserInfo(oAuth2AccessToken, null);
      response.getWriter().println("<h1>user info</h1>");
      response.getWriter().println(wxMpUser.toString());

      oAuth2AccessToken = this.weChatOfficialAccountService.getOAuth2Service().refreshAccessToken(oAuth2AccessToken.getRefreshToken());
      response.getWriter().println("<h1>after refresh</h1>");
      response.getWriter().println(oAuth2AccessToken.toString());

    } catch (WxErrorException e) {
      e.printStackTrace();
    }

    response.getWriter().flush();
    response.getWriter().close();

  }

}
