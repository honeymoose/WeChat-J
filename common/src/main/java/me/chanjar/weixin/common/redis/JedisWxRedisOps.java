package me.chanjar.weixin.common.redis;

import com.github.jedis.lock.JedisLock;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.util.locks.JedisDistributedLock;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.util.Pool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author Mario Luo
 *
 * @deprecated 该类底层使用过时的8年不维护的 {@link JedisLock}组件，不可靠，不建议继续使用。请
 * 使用：{@link  RedisTemplateWxRedisOps}、{@link  RedissonWxRedisOps} 替换
 */
@RequiredArgsConstructor
public class JedisWxRedisOps implements WxRedisOps {
  private final Pool<Jedis> jedisPool;

  @Override
  public String getValue(String key) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.get(key);
    }
  }

  @Override
  public void setValue(String key, String value, int expire, TimeUnit timeUnit) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      if (expire <= 0) {
        jedis.set(key, value);
      } else {
        jedis.psetex(key, timeUnit.toMillis(expire), value);
      }
    }
  }

  @Override
  public Long getExpire(String key) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.ttl(key);
    }
  }

  @Override
  public void expire(String key, int expire, TimeUnit timeUnit) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.pexpire(key, timeUnit.toMillis(expire));
    }
  }

  @Override
  public Lock getLock(String key) {
    return new JedisDistributedLock(jedisPool, key);
  }
}
