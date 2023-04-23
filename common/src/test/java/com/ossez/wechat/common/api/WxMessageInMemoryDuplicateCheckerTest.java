package com.ossez.wechat.common.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(Lifecycle.PER_CLASS)
@Slf4j
public class WxMessageInMemoryDuplicateCheckerTest {
    private WxMessageInMemoryDuplicateChecker checker = new WxMessageInMemoryDuplicateChecker(2000L, 1000L);

    @Test
    public void test() throws InterruptedException {
        Long[] msgIds = new Long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L};

        // 第一次检查
        for (Long msgId : msgIds) {
            boolean result = checker.isDuplicate(String.valueOf(msgId));
            assertThat(result).isFalse();
        }

        // 过1秒再检查
        TimeUnit.SECONDS.sleep(1);
        for (Long msgId : msgIds) {
            boolean result = checker.isDuplicate(String.valueOf(msgId));
            assertThat(result).isTrue();
        }

        // 过1.5秒再检查
        TimeUnit.MILLISECONDS.sleep(1500L);
        for (Long msgId : msgIds) {
            boolean result = checker.isDuplicate(String.valueOf(msgId));
            assertThat(result).isFalse();
        }
    }
}
