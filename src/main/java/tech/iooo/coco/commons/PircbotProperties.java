package tech.iooo.coco.commons;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created on 2018/7/5 下午1:49
 *
 * @author <a href="mailto:yangkizhang@gmail.com?subject=iooo-pircbotx-booster">Ivan97</a>
 */
@Data
@ConfigurationProperties(prefix = "pircbot", ignoreUnknownFields = false)
public class PircbotProperties {

  private String commandPrefix = ".";

  private boolean identServerEnable = false;

  private String name = "IoooBot";
  private String password = "";
  private String realName = name;
  private String login = "Ivan97";
  private String version = "0.0.1";
  private boolean autoNickChange = true;
  private boolean autoReconnect = true;
  private int autoReconnectAttempts = 3;
  private int autoReconnectDelay = 5000;

  private SslSocketType sslSocketType;

  private boolean tlsEnable = false;

  private List<String> channels = Lists.newArrayList("#SimpleBot");
  private List<ServerInfo> servers = Lists.newArrayList(
      ServerInfo.builder().hostname("irc.freenode.net").port(6697).build());

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ServerInfo {

    private String hostname;
    private int port;
  }

}
