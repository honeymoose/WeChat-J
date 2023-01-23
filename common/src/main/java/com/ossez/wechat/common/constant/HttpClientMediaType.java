package com.ossez.wechat.common.constant;

import com.google.common.net.MediaType;

/**
 * HttpClient send request the type constant.
 * Because okHttp need to use MediaType to construct object, but if we use guava MediaType, the two package will get conflict
 * at the same class.
 * <p>
 * So, we get MediaType String first before we convert to okHttp MediaType.
 *
 * @author YuCheng Hu
 */
public final class HttpClientMediaType {
    public static final String APPLICATION_JSON = MediaType.JSON_UTF_8.toString();
}
