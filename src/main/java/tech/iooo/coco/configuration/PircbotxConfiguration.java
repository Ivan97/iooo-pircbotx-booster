package tech.iooo.coco.configuration;

import java.util.Objects;
import java.util.stream.Collectors;
import javax.net.ssl.SSLSocketFactory;
import org.pircbotx.Configuration.Builder;
import org.pircbotx.Configuration.ServerEntry;
import org.pircbotx.IdentServer;
import org.pircbotx.PircBotX;
import org.pircbotx.UtilSSLSocketFactory;
import org.pircbotx.cap.SASLCapHandler;
import org.pircbotx.cap.TLSCapHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.iooo.coco.commons.CommandMatchingListenerManager;
import tech.iooo.coco.commons.PircbotProperties;

/**
 * Created on 2018/7/5 上午8:59
 *
 * @author <a href="mailto:yangkizhang@gmail.com?subject=iooo-pircbotx-booster">Ivan97</a>
 */
@EnableConfigurationProperties(PircbotProperties.class)
@Configuration
public class PircbotxConfiguration {

  @Autowired
  private PircbotProperties pircbotProperties;

  @Autowired
  private CommandMatchingListenerManager commandMatchingListenerManager;

  @Bean
  public PircBotX pircBotX() {

    if (pircbotProperties.isIdentServerEnable()) {
      IdentServer.startServer();
    }

    Builder builder = new Builder()
        .addServers(pircbotProperties.getServers().stream()
            .map(serverInfo -> new ServerEntry(serverInfo.getHostname(), serverInfo.getPort()))
            .collect(Collectors.toList()))
        .setName(pircbotProperties.getName())
        .setLogin(pircbotProperties.getLogin())
        .setAutoReconnect(pircbotProperties.isAutoReconnect())
        .setAutoReconnectAttempts(pircbotProperties.getAutoReconnectAttempts())
        .setAutoReconnectDelay(pircbotProperties.getAutoReconnectDelay())
        .setRealName(pircbotProperties.getRealName())
        .setVersion(pircbotProperties.getVersion())
        .setAutoNickChange(pircbotProperties.isAutoNickChange())
        .addAutoJoinChannels(pircbotProperties.getChannels())
        .setListenerManager(commandMatchingListenerManager);

    if (Objects.nonNull(pircbotProperties.getSslSocketType())) {
      switch (pircbotProperties.getSslSocketType()) {
        case DEFAULT:
          builder.setSocketFactory(SSLSocketFactory.getDefault());
          break;
        case DISABLE_DIFFIE_HELLMAN:
          builder.setSocketFactory(new UtilSSLSocketFactory().disableDiffieHellman());
          break;
        case TRUST_ALL_CERTIFICATES:
          builder.setSocketFactory(new UtilSSLSocketFactory().trustAllCertificates());
        default:
          break;
      }
    }
    if (pircbotProperties.isTlsEnable()) {
      pircbotProperties.getServers().forEach(server -> {
        if (server.getHostname().toLowerCase().contains("freenode.net")) {
          builder
              .addCapHandler(new SASLCapHandler(pircbotProperties.getName(), pircbotProperties.getPassword(), false));
        } else {
          builder.addCapHandler(new TLSCapHandler((SSLSocketFactory) SSLSocketFactory.getDefault(), true));
        }
      });
    }
    return new PircBotX(builder.buildConfiguration());
  }
}
