package com.ossez.wechat.oa.api.impl.okhttp.interceptor;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

/**
 * OkHttp Interceptor that adds an authorization token header
 */
public class AuthenticationInterceptor implements Interceptor {
    private final String token;

    public AuthenticationInterceptor(String token) {
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        HttpUrl url = chain.request().url().newBuilder().addQueryParameter("access_token", token).build();
        Request request = chain.request().newBuilder().url(url).build();
        //                .header("Authorization", "Bearer " + token)
        return chain.proceed(request);
    }
}
