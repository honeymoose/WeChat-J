package com.ossez.wechat.oa.bean.card.membercard;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 *
 * @author YuJian
 * @version 2017/7/15
 */
public class WxMpMemberCardUserInfoResultTest {

  @Test
  public void testFromJson() throws Exception {
    String json = "{\n" +
      "  \"errcode\": 0,\n" +
      "  \"errmsg\": \"ok\",\n" +
      "  \"openid\": \"obLatjjwDolFj******wNqRXw\",\n" +
      "  \"nickname\": \"*******\",\n" +
      "  \"membership_number\": \"658*****445\",\n" +
      "  \"bonus\": 995,\n" +
      "  \"sex\": \"MALE\",\n" +
      "  \"user_info\": {\n" +
      "    \"common_field_list\": [\n" +
      "      {\n" +
      "        \"name\": \"USER_FORM_INFO_FLAG_MOBILE\",\n" +
      "        \"value\": \"15*****518\"\n" +
      "      },\n" +
      "      {\n" +
      "        \"name\": \"USER_FORM_INFO_FLAG_NAME\",\n" +
      "        \"value\": \"HK\"\n" +
      "      },\n" +
      "      {\n" +
      "        \"name\": \"USER_FORM_INFO_FLAG_EDUCATION_BACKGROUND\",\n" +
      "        \"value\": \"研究生\"\n" +
      "      }\n" +
      "    ],\n" +
      "    \"custom_field_list\": [\n" +
      "      {\n" +
      "        \"name\": \"兴趣\",\n" +
      "        \"value\": \"钢琴\",\n" +
      "        \"value_list\": []\n" +
      "      },\n" +
      "      {\n" +
      "        \"name\": \"喜好\",\n" +
      "        \"value\": \"郭敬明\",\n" +
      "        \"value_list\": []\n" +
      "      },\n" +
      "      {\n" +
      "        \"name\": \"职业\",\n" +
      "        \"value\": \"\",\n" +
      "        \"value_list\": [\n" +
      "          \"赛车手\",\n" +
      "          \"旅行家\"\n" +
      "        ]\n" +
      "      }\n" +
      "    ]\n" +
      "  },\n" +
      "  \"user_card_status\": \"NORMAL\",\n" +
      "  \"has_active\": false\n" +
      "}";


    WxMpMemberCardUserInfoResult userInfoResult = WxMpMemberCardUserInfoResult.fromJson(json);

    Assert.assertNotNull(userInfoResult);
    Assert.assertFalse(userInfoResult.getHasActive());
    Assert.assertTrue(userInfoResult.getSex().equalsIgnoreCase("MALE"));
    Assert.assertNotNull(userInfoResult.getUserInfo());
    Assert.assertNotNull(userInfoResult.getUserInfo().getCommonFieldList());
    Assert.assertNotNull(userInfoResult.getUserInfo().getCustomFieldList());
    Assert.assertTrue(userInfoResult.getUserInfo().getCommonFieldList().length == 3);
    Assert.assertTrue(userInfoResult.getUserInfo().getCustomFieldList()[2].getValueList()[0].equalsIgnoreCase("赛车手"));

    System.out.println(userInfoResult);
  }
}
