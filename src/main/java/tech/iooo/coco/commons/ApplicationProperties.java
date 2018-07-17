package tech.iooo.coco.commons;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/7/5 下午1:49
 *
 * @author <a href="mailto:yangkizhang@gmail.com?subject=iooo-pircbotx-booster">Ivan97</a>
 */
@Data
@Component
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

  private ApplicationProperties.WryDat wryDat = new ApplicationProperties.WryDat();

  @Data
  public static class WryDat {

    private String updateTime;
  }

}
