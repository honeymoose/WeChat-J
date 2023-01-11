package me.chanjar.weixin.mp.bean.message;

import com.ossez.wechat.common.api.WxConsts;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

@Test
public class WxMpXmlMessageTest {

  public void testFromXml() {

    String xml = "<xml>"
      + "<ToUserName><![CDATA[toUser]]></ToUserName>"
      + "<FromUserName><![CDATA[fromUser]]></FromUserName> "
      + "<CreateTime>1348831860</CreateTime>"
      + "<MsgType><![CDATA[text]]></MsgType>"
      + "<Content><![CDATA[this is a test]]></Content>"
      + "<MsgId>1234567890123456</MsgId>"
      + "<PicUrl><![CDATA[this is a url]]></PicUrl>"
      + "<MediaId><![CDATA[media_id]]></MediaId>"
      + "<Format><![CDATA[Format]]></Format>"
      + "<ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>"
      + "<Location_X>23.134521</Location_X>"
      + "<Location_Y>113.358803</Location_Y>"
      + "<Scale>20</Scale>"
      + "<Label><![CDATA[位置信息]]></Label>"
      + "<Description><![CDATA[公众平台官网链接]]></Description>"
      + "<Url><![CDATA[url]]></Url>"
      + "<Title><![CDATA[公众平台官网链接]]></Title>"
      + "<Event><![CDATA[subscribe]]></Event>"
      + "<EventKey><![CDATA[qrscene_123123]]></EventKey>"
      + "<Ticket><![CDATA[TICKET]]></Ticket>"
      + "<Latitude>23.137466</Latitude>"
      + "<Longitude>113.352425</Longitude>"
      + "<Precision>119.385040</Precision>"
      + "<ScanCodeInfo>"
      + " <ScanType><![CDATA[qrcode]]></ScanType>"
      + " <ScanResult><![CDATA[1]]></ScanResult>"
      + "</ScanCodeInfo>"
      + "<SendPicsInfo>"
      + " <Count>1</Count>"
      + " <PicList>"
      + "  <item>"
      + "   <PicMd5Sum><![CDATA[1b5f7c23b5bf75682a53e7b6d163e185]]></PicMd5Sum>"
      + "  </item>"
      + " </PicList>"
      + "</SendPicsInfo>"
      + "<SendLocationInfo>"
      + "  <Location_X><![CDATA[23]]></Location_X>"
      + "  <Location_Y><![CDATA[113]]></Location_Y>"
      + "  <Scale><![CDATA[15]]></Scale>"
      + "  <Label><![CDATA[ 广州市海珠区客村艺苑路 106号]]></Label>"
      + "  <Poiname><![CDATA[wo de poi]]></Poiname>"
      + "</SendLocationInfo>"
      + "<KeyStandard><![CDATA[ean13]]></KeyStandard>"
      + "<KeyStr><![CDATA[6901481811083]]></KeyStr>"
      + "<Country><![CDATA[中国]]></Country>"
      + "<Province><![CDATA[广东]]></Province>"
      + "<City><![CDATA[揭阳]]></City>"
      + "<Sex>1</Sex>"
      + "<Scene>2</Scene>"
      + "<ExtInfo><![CDATA[123]]></ExtInfo>"
      + "<RegionCode><![CDATA[440105]]></RegionCode>"
      + "<ReasonMsg><![CDATA[]]></ReasonMsg>"
      + "</xml>";
    WxMpXmlMessage wxMessage = WxMpXmlMessage.fromXml(xml);
    assertEquals(wxMessage.getToUser(), "toUser");
    assertEquals(wxMessage.getFromUser(), "fromUser");
    assertEquals(wxMessage.getCreateTime(), new Long(1348831860L));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.TEXT);
    assertEquals(wxMessage.getContent(), "this is a test");
    assertEquals(wxMessage.getMsgId(), new Long(1234567890123456L));
    assertEquals(wxMessage.getPicUrl(), "this is a url");
    assertEquals(wxMessage.getMediaId(), "media_id");
    assertEquals(wxMessage.getFormat(), "Format");
    assertEquals(wxMessage.getThumbMediaId(), "thumb_media_id");
    assertEquals(wxMessage.getLocationX().doubleValue(), 23.134521d);
    assertEquals(wxMessage.getLocationY().doubleValue(), 113.358803d);
    assertEquals(wxMessage.getScale().doubleValue(), 20d);
    assertEquals(wxMessage.getLabel(), "位置信息");
    assertEquals(wxMessage.getDescription(), "公众平台官网链接");
    assertEquals(wxMessage.getUrl(), "url");
    assertEquals(wxMessage.getTitle(), "公众平台官网链接");
    assertEquals(wxMessage.getEvent(), "subscribe");
    assertEquals(wxMessage.getEventKey(), "qrscene_123123");
    assertEquals(wxMessage.getTicket(), "TICKET");
    assertEquals(wxMessage.getLatitude().doubleValue(), 23.137466);
    assertEquals(wxMessage.getLongitude().doubleValue(), 113.352425);
    assertEquals(wxMessage.getPrecision().doubleValue(), 119.385040);
    assertEquals(wxMessage.getScanCodeInfo().getScanType(), "qrcode");
    assertEquals(wxMessage.getScanCodeInfo().getScanResult(), "1");
    assertEquals(wxMessage.getSendPicsInfo().getCount(), new Long(1L));
    assertEquals(wxMessage.getSendPicsInfo().getPicList().get(0).getPicMd5Sum(), "1b5f7c23b5bf75682a53e7b6d163e185");
    assertEquals(wxMessage.getSendLocationInfo().getLocationX(), "23");
    assertEquals(wxMessage.getSendLocationInfo().getLocationY(), "113");
    assertEquals(wxMessage.getSendLocationInfo().getScale(), "15");
    assertEquals(wxMessage.getSendLocationInfo().getLabel(), " 广州市海珠区客村艺苑路 106号");
    assertEquals(wxMessage.getSendLocationInfo().getPoiName(), "wo de poi");
    assertEquals(wxMessage.getKeyStandard(), "ean13");
    assertEquals(wxMessage.getKeyStr(), "6901481811083");
    assertEquals(wxMessage.getCountry(), "中国");
    assertEquals(wxMessage.getProvince(), "广东");
    assertEquals(wxMessage.getCity(), "揭阳");
    assertEquals(wxMessage.getSex(), "1");
    assertEquals(wxMessage.getScene(), "2");
    assertEquals(wxMessage.getExtInfo(), "123");
    assertEquals(wxMessage.getRegionCode(), "440105");
    assertEquals(wxMessage.getReasonMsg(), "");
  }

  public void testFromXml2() {

    String xml = "<xml>"
      + "<ToUserName><![CDATA[toUser]]></ToUserName>"
      + "<FromUserName><![CDATA[fromUser]]></FromUserName> "
      + "<CreateTime>1348831860</CreateTime>"
      + "<MsgType><![CDATA[text]]></MsgType>"
      + "<Content><![CDATA[this is a test]]></Content>"
      + "<MsgID>1234567890123456</MsgID>"
      + "<PicUrl><![CDATA[this is a url]]></PicUrl>"
      + "<MediaId><![CDATA[media_id]]></MediaId>"
      + "<Format><![CDATA[Format]]></Format>"
      + "<ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>"
      + "<Location_X>23.134521</Location_X>"
      + "<Location_Y>113.358803</Location_Y>"
      + "<Scale>20</Scale>"
      + "<Label><![CDATA[位置信息]]></Label>"
      + "<Description><![CDATA[公众平台官网链接]]></Description>"
      + "<Url><![CDATA[url]]></Url>"
      + "<Title><![CDATA[公众平台官网链接]]></Title>"
      + "<Event><![CDATA[subscribe]]></Event>"
      + "<EventKey><![CDATA[qrscene_123123]]></EventKey>"
      + "<Ticket><![CDATA[TICKET]]></Ticket>"
      + "<Latitude>23.137466</Latitude>"
      + "<Longitude>113.352425</Longitude>"
      + "<Precision>119.385040</Precision>"
      + "<ScanCodeInfo>"
      + " <ScanType><![CDATA[qrcode]]></ScanType>"
      + " <ScanResult><![CDATA[1]]></ScanResult>"
      + "</ScanCodeInfo>"
      + "<SendPicsInfo>"
      + " <Count>1</Count>\n"
      + " <PicList>"
      + "  <item>"
      + "   <PicMd5Sum><![CDATA[1b5f7c23b5bf75682a53e7b6d163e185]]></PicMd5Sum>"
      + "  </item>"
      + " </PicList>"
      + "</SendPicsInfo>"
      + "<SendLocationInfo>"
      + "  <Location_X><![CDATA[23]]></Location_X>\n"
      + "  <Location_Y><![CDATA[113]]></Location_Y>\n"
      + "  <Scale><![CDATA[15]]></Scale>\n"
      + "  <Label><![CDATA[ 广州市海珠区客村艺苑路 106号]]></Label>\n"
      + "  <Poiname><![CDATA[wo de poi]]></Poiname>\n"
      + "</SendLocationInfo>"
      + "</xml>";
    WxMpXmlMessage wxMessage = WxMpXmlMessage.fromXml(xml);
    assertEquals(wxMessage.getToUser(), "toUser");
    assertEquals(wxMessage.getFromUser(), "fromUser");
    assertEquals(wxMessage.getCreateTime(), new Long(1348831860L));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.TEXT);
    assertEquals(wxMessage.getContent(), "this is a test");
    assertEquals(wxMessage.getMsgId(), new Long(1234567890123456L));
    assertEquals(wxMessage.getPicUrl(), "this is a url");
    assertEquals(wxMessage.getMediaId(), "media_id");
    assertEquals(wxMessage.getFormat(), "Format");
    assertEquals(wxMessage.getThumbMediaId(), "thumb_media_id");
    assertEquals(wxMessage.getLocationX().doubleValue(), 23.134521d);
    assertEquals(wxMessage.getLocationY().doubleValue(), 113.358803d);
    assertEquals(wxMessage.getScale().doubleValue(), 20d);
    assertEquals(wxMessage.getLabel(), "位置信息");
    assertEquals(wxMessage.getDescription(), "公众平台官网链接");
    assertEquals(wxMessage.getUrl(), "url");
    assertEquals(wxMessage.getTitle(), "公众平台官网链接");
    assertEquals(wxMessage.getEvent(), "subscribe");
    assertEquals(wxMessage.getEventKey(), "qrscene_123123");
    assertEquals(wxMessage.getTicket(), "TICKET");
    assertEquals(wxMessage.getLatitude().doubleValue(), 23.137466);
    assertEquals(wxMessage.getLongitude().doubleValue(), 113.352425);
    assertEquals(wxMessage.getPrecision().doubleValue(), 119.385040);
    assertEquals(wxMessage.getScanCodeInfo().getScanType(), "qrcode");
    assertEquals(wxMessage.getScanCodeInfo().getScanResult(), "1");
    assertEquals(wxMessage.getSendPicsInfo().getCount(), new Long(1L));
    assertEquals(wxMessage.getSendPicsInfo().getPicList().get(0).getPicMd5Sum(), "1b5f7c23b5bf75682a53e7b6d163e185");
    assertEquals(wxMessage.getSendLocationInfo().getLocationX(), "23");
    assertEquals(wxMessage.getSendLocationInfo().getLocationY(), "113");
    assertEquals(wxMessage.getSendLocationInfo().getScale(), "15");
    assertEquals(wxMessage.getSendLocationInfo().getLabel(), " 广州市海珠区客村艺苑路 106号");
    assertEquals(wxMessage.getSendLocationInfo().getPoiName(), "wo de poi");
  }

  public void testFromXml_MASSSENDJOBFINISH() {
    //xml样例来自 https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1481187827_i0l21
    String xml = "<xml>\n" +
      "<ToUserName><![CDATA[gh_4d00ed8d6399]]></ToUserName>\n" +
      "<FromUserName><![CDATA[oV5CrjpxgaGXNHIQigzNlgLTnwic]]></FromUserName>\n" +
      "<CreateTime>1481013459</CreateTime>\n" +
      "<MsgType><![CDATA[event]]></MsgType>\n" +
      "<Event><![CDATA[MASSSENDJOBFINISH]]></Event>\n" +
      "<MsgID>1000001625</MsgID>\n" +
      "<Status><![CDATA[err(30003)]]></Status>\n" +
      "<TotalCount>0</TotalCount>\n" +
      "<FilterCount>0</FilterCount>\n" +
      "<SentCount>0</SentCount>\n" +
      "<ErrorCount>0</ErrorCount>\n" +
      "<CopyrightCheckResult>\n" +
      "<Count>2</Count>\n" +
      "<ResultList>\n" +
      "<item>\n" +
      "<ArticleIdx>1</ArticleIdx>\n" +
      "<UserDeclareState>0</UserDeclareState>\n" +
      "<AuditState>2</AuditState>\n" +
      "<OriginalArticleUrl><![CDATA[Url_1]]></OriginalArticleUrl>\n" +
      "<OriginalArticleType>1</OriginalArticleType>\n" +
      "<CanReprint>1</CanReprint>\n" +
      "<NeedReplaceContent>1</NeedReplaceContent>\n" +
      "<NeedShowReprintSource>1</NeedShowReprintSource>\n" +
      "</item>\n" +
      "<item>\n" +
      "<ArticleIdx>2</ArticleIdx>\n" +
      "<UserDeclareState>0</UserDeclareState>\n" +
      "<AuditState>2</AuditState>\n" +
      "<OriginalArticleUrl><![CDATA[Url_2]]></OriginalArticleUrl>\n" +
      "<OriginalArticleType>1</OriginalArticleType>\n" +
      "<CanReprint>1</CanReprint>\n" +
      "<NeedReplaceContent>1</NeedReplaceContent>\n" +
      "<NeedShowReprintSource>1</NeedShowReprintSource>\n" +
      "</item>\n" +
      "</ResultList>\n" +
      "<CheckState>2</CheckState>\n" +
      "</CopyrightCheckResult>\n" +
      "</xml>";
    WxMpXmlMessage wxMessage = WxMpXmlMessage.fromXml(xml);
    assertEquals(wxMessage.getToUser(), "gh_4d00ed8d6399");
    assertEquals(wxMessage.getFromUser(), "oV5CrjpxgaGXNHIQigzNlgLTnwic");
    assertEquals(wxMessage.getCreateTime(), new Long(1481013459));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.EVENT);
    assertEquals(wxMessage.getEvent(), "MASSSENDJOBFINISH");
    assertEquals(wxMessage.getMsgId(), new Long(1000001625L));
    assertEquals(wxMessage.getStatus(), "err(30003)");
    assertEquals(wxMessage.getTotalCount(), new Integer(0));
    assertEquals(wxMessage.getFilterCount(), new Integer(0));
    assertEquals(wxMessage.getSentCount(), new Integer(0));
    assertEquals(wxMessage.getErrorCount(), new Integer(0));

    final Map<String, Object> allFields = wxMessage.getAllFieldsMap();
    Assertions.assertThat(allFields).isNotNull();
    final Map<String, Object> copyrightCheckResult = (Map<String, Object>) allFields.get("CopyrightCheckResult");
    List<Map<String, Object>> resultList = (List<Map<String, Object>>) ((Map<String, Object>) copyrightCheckResult
      .get("ResultList")).get("item");
    Assertions.assertThat(copyrightCheckResult).isNotNull();

    Assertions.assertThat(copyrightCheckResult.get("Count")).isEqualTo("2");
    Assertions.assertThat(copyrightCheckResult.get("CheckState")).isEqualTo("2");

    Assertions.assertThat(resultList.get(0).get("ArticleIdx")).isEqualTo("1");
    Assertions.assertThat(resultList.get(0).get("UserDeclareState")).isEqualTo("0");
    Assertions.assertThat(resultList.get(0).get("AuditState")).isEqualTo("2");
    Assertions.assertThat(resultList.get(0).get("OriginalArticleUrl")).isEqualTo("Url_1");
    Assertions.assertThat(resultList.get(0).get("OriginalArticleType")).isEqualTo("1");
    Assertions.assertThat(resultList.get(0).get("CanReprint")).isEqualTo("1");
    Assertions.assertThat(resultList.get(0).get("NeedReplaceContent")).isEqualTo("1");
    Assertions.assertThat(resultList.get(0).get("NeedShowReprintSource")).isEqualTo("1");

    Assertions.assertThat(resultList.get(1).get("ArticleIdx")).isEqualTo("2");
    Assertions.assertThat(resultList.get(1).get("UserDeclareState")).isEqualTo("0");
    Assertions.assertThat(resultList.get(1).get("AuditState")).isEqualTo("2");
    Assertions.assertThat(resultList.get(1).get("OriginalArticleUrl")).isEqualTo("Url_2");
    Assertions.assertThat(resultList.get(1).get("OriginalArticleType")).isEqualTo("1");
    Assertions.assertThat(resultList.get(1).get("CanReprint")).isEqualTo("1");
    Assertions.assertThat(resultList.get(1).get("NeedReplaceContent")).isEqualTo("1");
    Assertions.assertThat(resultList.get(1).get("NeedShowReprintSource")).isEqualTo("1");
  }

  public void testSubMsgPopupFromXml() {

    String xml = "<xml>"
      + "<ToUserName><![CDATA[gh_123456789abc]]></ToUserName>"
      + "<FromUserName><![CDATA[otFpruAK8D-E6EfStSYonYSBZ8_4]]></FromUserName>"
      + "<CreateTime>1610969440</CreateTime>"
      + "<MsgType><![CDATA[event]]></MsgType>"
      + "<Event><![CDATA[subscribe_msg_popup_event]]></Event>"
      + "<SubscribeMsgPopupEvent>"
      + "<List>"
      + "<TemplateId><![CDATA[VRR0UEO9VJOLs0MHlU0OilqX6MVFDwH3_3gz3Oc0NIc]]></TemplateId>"
      + "<SubscribeStatusString><![CDATA[accept]]></SubscribeStatusString>"
      + "<PopupScene>2</PopupScene>"
      + "</List>"
      + "<List>"
      + "<TemplateId><![CDATA[9nLIlbOQZC5Y89AZteFEux3WCXRRRG5Wfzkpssu4bLI]]></TemplateId>"
      + "<SubscribeStatusString><![CDATA[reject]]></SubscribeStatusString>"
      + "<PopupScene>2</PopupScene>"
      + "</List>"
      + "</SubscribeMsgPopupEvent>"
      + "</xml>";

    WxMpXmlMessage wxMessage = WxMpXmlMessage.fromXml(xml);
    WxMpSubscribeMsgEvent.PopupEvent popupEvent = wxMessage.getSubscribeMsgPopupEvent().getList().get(0);
    assertEquals(popupEvent.getTemplateId(), "VRR0UEO9VJOLs0MHlU0OilqX6MVFDwH3_3gz3Oc0NIc");
    assertEquals(popupEvent.getSubscribeStatusString(), "accept");
    assertEquals(popupEvent.getPopupScene(), "2");
    WxMpSubscribeMsgEvent.PopupEvent popupEvent2 = wxMessage.getSubscribeMsgPopupEvent().getList().get(1);
    assertEquals(popupEvent2.getTemplateId(), "9nLIlbOQZC5Y89AZteFEux3WCXRRRG5Wfzkpssu4bLI");
    assertEquals(popupEvent2.getSubscribeStatusString(), "reject");
    assertEquals(popupEvent2.getPopupScene(), "2");
  }

  public void testSubMsgChangeFromXml() {

    String xml = "<xml>"
      + "<ToUserName><![CDATA[gh_123456789abc]]></ToUserName>"
      + "<FromUserName><![CDATA[otFpruAK8D-E6EfStSYonYSBZ8_4]]></FromUserName>"
      + "<CreateTime>1610969440</CreateTime>"
      + "<MsgType><![CDATA[event]]></MsgType>"
      + "<Event><![CDATA[subscribe_msg_change_event]]></Event>"
      + "<SubscribeMsgChangeEvent>"
      + "<List>"
      + "<TemplateId><![CDATA[VRR0UEO9VJOLs0MHlU0OilqX6MVFDwH3_3gz3Oc0NIc]]></TemplateId>"
      + "<SubscribeStatusString><![CDATA[reject]]></SubscribeStatusString>"
      + "</List>"
      + "</SubscribeMsgChangeEvent>"
      + "</xml>";

    WxMpXmlMessage wxMessage = WxMpXmlMessage.fromXml(xml);
    WxMpSubscribeMsgEvent.ChangeEvent changeEvent = wxMessage.getSubscribeMsgChangeEvent().getList().get(0);
    assertEquals(changeEvent.getTemplateId(), "VRR0UEO9VJOLs0MHlU0OilqX6MVFDwH3_3gz3Oc0NIc");
    assertEquals(changeEvent.getSubscribeStatusString(), "reject");
  }

  public void testSubMsgSentFromXml() {

    String xml = "<xml>"
      + "<ToUserName><![CDATA[gh_123456789abc]]></ToUserName>"
      + "<FromUserName><![CDATA[otFpruAK8D-E6EfStSYonYSBZ8_4]]></FromUserName>"
      + "<CreateTime>1610969440</CreateTime>"
      + "<MsgType><![CDATA[event]]></MsgType>"
      + "<Event><![CDATA[subscribe_msg_sent_event]]></Event>"
      + "<SubscribeMsgSentEvent>"
      + "<List>"
      + "<TemplateId><![CDATA[VRR0UEO9VJOLs0MHlU0OilqX6MVFDwH3_3gz3Oc0NIc]]></TemplateId>"
      + "<MsgID>1700827132819554304</MsgID>"
      + "<ErrorCode>0</ErrorCode>"
      + "<ErrorStatus><![CDATA[success]]></ErrorStatus>"
      + "</List>"
      + "</SubscribeMsgSentEvent>"
      + "</xml>";

    WxMpXmlMessage wxMessage = WxMpXmlMessage.fromXml(xml);
    WxMpSubscribeMsgEvent.SentEvent sentEvent = wxMessage.getSubscribeMsgSentEvent().getList().get(0);
    assertEquals(sentEvent.getTemplateId(), "VRR0UEO9VJOLs0MHlU0OilqX6MVFDwH3_3gz3Oc0NIc");
    assertEquals(sentEvent.getMsgId(), "1700827132819554304");
    assertEquals(sentEvent.getErrorCode(), "0");
    assertEquals(sentEvent.getErrorStatus(), "success");
  }
}
