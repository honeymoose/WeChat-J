package com.ossez.wechat.wecom.tp.service.impl;

import com.ossez.wechat.common.bean.result.WxMediaUploadResult;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.fs.FileUtils;
import com.ossez.wechat.common.util.http.MediaUploadRequestExecutor;
import com.ossez.wechat.wecom.constant.WxCpApiPathConsts;
import lombok.RequiredArgsConstructor;
import com.ossez.wechat.wecom.tp.service.WxCpTpMediaService;
import com.ossez.wechat.wecom.tp.service.WxCpTpService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * <pre>
 * 媒体管理接口.
 * Created by Binary Wang on 2017-6-25.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RequiredArgsConstructor
public class WxCpTpMediaServiceImpl implements WxCpTpMediaService {
    private final WxCpTpService mainService;

    @Override
    public WxMediaUploadResult upload(String mediaType, String fileType, InputStream inputStream, String corpId)
            throws WxErrorException, IOException {
        return this.upload(mediaType, FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), fileType), corpId);
    }

    @Override
    public WxMediaUploadResult upload(String mediaType, File file, String corpId) throws WxErrorException {
        return this.mainService.execute(MediaUploadRequestExecutor.create(this.mainService.getRequestHttp()),
                mainService.getWxCpTpConfigStorage().getApiUrl(WxCpApiPathConsts.Media.MEDIA_UPLOAD + mediaType) + "&access_token=" + mainService.getWxCpTpConfigStorage().getAccessToken(corpId), file);
    }

    @Override
    public String uploadImg(File file, String corpId) throws WxErrorException {
        String url = mainService.getWxCpTpConfigStorage().getApiUrl(WxCpApiPathConsts.Media.IMG_UPLOAD);
        url += "?access_token=" + mainService.getWxCpTpConfigStorage().getAccessToken(corpId);
        return this.mainService.execute(MediaUploadRequestExecutor.create(this.mainService.getRequestHttp()), url, file)
                .getUrl();
    }
}
