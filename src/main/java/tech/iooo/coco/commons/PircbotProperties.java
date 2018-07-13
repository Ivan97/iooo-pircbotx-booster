package tech.iooo.coco.commons;

import java.util.Collections;
import java.util.List;
import lombok.Data;
import org.pircbotx.Configuration.ServerEntry;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Created on 2018/7/5 下午1:49
 *
 * @author <a href="mailto:yangkizhang@gmail.com?subject=iooo-pircbotx-booster">Ivan97</a>
 */
@Data
@ConfigurationProperties(prefix = "pircbot")
public class PircbotProperties {

  @NestedConfigurationProperty
  private static final List<String> DEFAULT_CHANNELS = Collections.singletonList("#SimpleBot");
  @NestedConfigurationProperty
  private static final List<ServerEntry> DEFAULT_SERVERS = Collections
      .singletonList(new ServerEntry("irc.freenode.net", 6667));

  private String commandPrefix = ".";

  private boolean identServerEnable = false;

  private String name = "SimpleBot";
  private String realName = name;
  private String login = "Ivan97";
  private String version = "0.0.1";
  private boolean autoNickChange = true;
  private boolean autoReconnect = true;
  private int autoReconnectAttempts = 3;
  private int autoReconnectDelay = 5000;

  private SslSocketType sslSocketType;

  private boolean tlsEnable = false;

  private List<String> channels = DEFAULT_CHANNELS;
  private List<ServerEntry> servers = DEFAULT_SERVERS;

}
