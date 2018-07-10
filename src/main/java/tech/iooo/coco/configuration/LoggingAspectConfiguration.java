package tech.iooo.coco.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import tech.iooo.coco.aop.logger.LoggingAspect;

/**
 * @author Ivan97
 */
@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {

  @Bean
  @Profile(ApplicationConstants.SPRING_PROFILE_DEVELOPMENT)
  public LoggingAspect loggingAspect(Environment env) {
    return new LoggingAspect(env);
  }
}
