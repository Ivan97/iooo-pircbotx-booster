package tech.iooo.coco.configuration;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2018/7/5 下午2:42
 *
 * @author <a href="mailto:yangkizhang@gmail.com?subject=iooo-pircbotx-booster">Ivan97</a>
 */
@Configuration
public class ApplicationConfiguration {

  @Bean
  public ExecutorService executorService() {
    ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("i-exec-pool-%d").setDaemon(false).build();
    return new ThreadPoolExecutor(5, 100, 0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingDeque<>(1024), threadFactory, new AbortPolicy());
  }

  @Bean
  public ScheduledExecutorService scheduledExecutorService() {
    return new ScheduledThreadPoolExecutor(1,
        new BasicThreadFactory.Builder().namingPattern("i-sched-pool-%d").daemon(false).build());
  }
}
