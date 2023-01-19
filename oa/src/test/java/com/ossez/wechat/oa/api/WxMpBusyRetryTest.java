package com.ossez.wechat.oa.api;

import com.ossez.wechat.oa.api.impl.WeChatOfficialAccountServiceHttpClientImpl;
import lombok.extern.slf4j.Slf4j;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.exception.WxRuntimeException;
import com.ossez.wechat.common.util.http.RequestExecutor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Test
@Slf4j
public class WxMpBusyRetryTest {

  @DataProvider(name = "getService")
  public Object[][] getService() {
    WeChatOfficialAccountService service = new WeChatOfficialAccountServiceHttpClientImpl() {

      @Override
      public synchronized <T, E> T executeInternal(
        RequestExecutor<T, E> executor, String uri, E data, boolean doNotAutoRefresh)
        throws WxErrorException {
        log.info("Executed");
        throw new WxErrorException("something");
      }
    };

    service.setMaxRetryTimes(3);
    service.setRetrySleepMillis(500);
    return new Object[][]{{service}};
  }

  @Test(dataProvider = "getService", expectedExceptions = RuntimeException.class)
  public void testRetry(WeChatOfficialAccountService service) throws WxErrorException {
    service.execute(null, (String) null, null);
  }

  @Test(dataProvider = "getService")
  public void testRetryInThreadPool(final WeChatOfficialAccountService service) throws InterruptedException, ExecutionException {
    // 当线程池中的线程复用的时候，还是能保证相同的重试次数
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    Runnable runnable = () -> {
      try {
        System.out.println("=====================");
        System.out.println(Thread.currentThread().getName() + ": testRetry");
        service.execute(null, (String) null, null);
      } catch (WxErrorException e) {
        throw new WxRuntimeException(e);
      } catch (RuntimeException e) {
        // OK
      }
    };
    Future<?> submit1 = executorService.submit(runnable);
    Future<?> submit2 = executorService.submit(runnable);

    submit1.get();
    submit2.get();
  }

}
