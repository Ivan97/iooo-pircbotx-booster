package tech.iooo.coco.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2018/7/10 下午3:08
 *
 * @author <a href="mailto:yangkizhang@gmail.com?subject=iooo-pircbotx-booster">Ivan97</a>
 */
@UtilityClass
public class CacheKit {

  private static final Logger logger = LoggerFactory.getLogger(CacheKit.class);
  private static final Cache<Object, Object> CACHE = CacheBuilder.newBuilder()
      .maximumSize(1000)
      .removalListener(notification -> {
        if (logger.isDebugEnabled()) {
          logger.debug("cache [{}] removed:{}", notification.getKey(), notification.getCause());
        }
      }).build();

  public static Cache<Object, Object> cache() {
    return CACHE;
  }
}
