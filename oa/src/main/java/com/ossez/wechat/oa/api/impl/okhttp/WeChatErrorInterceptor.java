package com.ossez.wechat.oa.api.impl.okhttp;

import com.ossez.wechat.common.constant.HttpClientMediaType;
import okhttp3.*;
import okio.Buffer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * The reason we have Interceptor here was because will return code 200 with error message.
 * <p>
 * For example, if we have error secret and try to get access token.
 * WeChat will return code 200 success with json error message like:
 *
 * <pre>
 *   {"errcode":40125,"errmsg":"invalid appsecret rid: 63cf14c3-1af7da21-37efbc86"}
 * </pre>
 * <p>
 * We need to check the response content, if the response content has errcode.
 * We Interceptor response with error code 400 and add error json format to response body.
 *
 * @author YuCheng Hu
 */
public class WeChatErrorInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        ResponseBody responseBody = response.body();
        Buffer buffer = responseBody.source().getBuffer();
        String responseStr = buffer.clone().readString(StandardCharsets.UTF_8);

        if (StringUtils.contains(responseStr, "errcode")) {
            responseBody = ResponseBody.create(MediaType.get(HttpClientMediaType.APPLICATION_JSON), responseStr);
            return response.newBuilder().code(400).body(responseBody).build();
        }
        return response;
    }
}
