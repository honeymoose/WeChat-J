package com.ossez.wechat.oa.demo;

import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.oa.api.WxMpMessageHandler;
import com.ossez.wechat.oa.api.WxMpMessageRouter;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.impl.WeChatOfficialAccountServiceHttpClientImpl;
import com.ossez.wechat.oa.config.WxMpConfigStorage;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.IOException;
import java.io.InputStream;

public class WxMpDemoServer {

  private static WxMpConfigStorage wxMpConfigStorage;
  private static WeChatOfficialAccountService weChatOfficialAccountService;
  private static WxMpMessageRouter wxMpMessageRouter;

  public static void main(String[] args) throws Exception {
    initWeixin();

    Server server = new Server(8080);

    ServletHandler servletHandler = new ServletHandler();
    server.setHandler(servletHandler);

    ServletHolder endpointServletHolder = new ServletHolder(new WxMpEndpointServlet(wxMpConfigStorage, weChatOfficialAccountService,
      wxMpMessageRouter));
    servletHandler.addServletWithMapping(endpointServletHolder, "/*");

    ServletHolder oauthServletHolder = new ServletHolder(new WxMpOAuth2Servlet(weChatOfficialAccountService));
    servletHandler.addServletWithMapping(oauthServletHolder, "/oauth2/*");

    server.start();
    server.join();
  }

  private static void initWeixin() {
    try (InputStream is1 = ClassLoader.getSystemResourceAsStream("test-config.xml")) {
      WxMpDemoInMemoryConfigStorage config = WxMpDemoInMemoryConfigStorage.fromXml(is1);

      wxMpConfigStorage = config;
      weChatOfficialAccountService = new WeChatOfficialAccountServiceHttpClientImpl();
      weChatOfficialAccountService.setWxMpConfigStorage(config);

      WxMpMessageHandler logHandler = new DemoLogHandler();
      WxMpMessageHandler textHandler = new DemoTextHandler();
      WxMpMessageHandler imageHandler = new DemoImageHandler();
      WxMpMessageHandler oauth2handler = new DemoOAuth2Handler();
      DemoGuessNumberHandler guessNumberHandler = new DemoGuessNumberHandler();

      wxMpMessageRouter = new WxMpMessageRouter(weChatOfficialAccountService);
      wxMpMessageRouter.rule().handler(logHandler).next().rule()
        .msgType(WeChatConstant.XmlMsgType.TEXT).matcher(guessNumberHandler)
        .handler(guessNumberHandler).end().rule().async(false).content("哈哈")
        .handler(textHandler).end().rule().async(false).content("图片")
        .handler(imageHandler).end().rule().async(false).content("oauth")
        .handler(oauth2handler).end();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
