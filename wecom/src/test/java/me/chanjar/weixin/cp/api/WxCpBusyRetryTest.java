package me.chanjar.weixin.cp.api;

import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.exception.WxRuntimeException;
import com.ossez.wechat.common.util.http.RequestExecutor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * The type Wx cp busy retry test.
 */
@Test
@Slf4j
public class WxCpBusyRetryTest {
  /**
   * Get service object [ ] [ ].
   *
   * @return the object [ ] [ ]
   */
  @DataProvider(name = "getService")
  public Object[][] getService() {
    WxCpService service = new WxCpServiceImpl() {

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
    return new Object[][]{
      new Object[]{service}
    };
  }

  /**
   * Test retry.
   *
   * @param service the service
   * @throws WxErrorException the wx error exception
   */
  @Test(dataProvider = "getService", expectedExceptions = RuntimeException.class)
  public void testRetry(WxCpService service) throws WxErrorException {
    service.execute(null, null, null);
  }

  /**
   * Test retry in thread pool.
   *
   * @param service the service
   * @throws InterruptedException the interrupted exception
   * @throws ExecutionException   the execution exception
   */
  @Test(dataProvider = "getService")
  public void testRetryInThreadPool(final WxCpService service) throws InterruptedException, ExecutionException {
    // 当线程池中的线程复用的时候，还是能保证相同的重试次数
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    Runnable runnable = () -> {
      try {
        System.out.println("=====================");
        System.out.println(Thread.currentThread().getName() + ": testRetry");
        service.execute(null, null, null);
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
