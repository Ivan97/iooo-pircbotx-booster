package tech.iooo.coco.handler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.Callable;
import org.pircbotx.hooks.events.PingEvent;
import org.pircbotx.hooks.events.TimeEvent;
import org.pircbotx.hooks.events.TopicEvent;
import org.pircbotx.hooks.events.UnknownEvent;
import org.pircbotx.hooks.events.UserListEvent;
import org.pircbotx.hooks.events.WhoisEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.iooo.coco.commons.IrcEventListener;
import tech.iooo.coco.utils.CacheKit;
import tech.iooo.coco.utils.UnknownEventMessageResolver;

/**
 * Created on 2018/7/6 下午2:48
 *
 * @author <a href="mailto:yangkizhang@gmail.com?subject=iooo-pircbotx-booster">Ivan97</a>
 */
@IrcEventListener
public class CommonEventListener extends BaseEventListener {

  private static final Logger logger = LoggerFactory.getLogger(CommonEventListener.class);
  private static final String ACCESS_KEY = "accessed";

  @Override
  public void onTopic(TopicEvent event) {
    String channelName = event.getChannel().getName();
    logger.info("channel:[{}] [{}]", channelName, event.getTopic());
  }

  @Override
  public void onUserList(UserListEvent event) throws Exception {

    CacheKit.cache().get(ACCESS_KEY, (Callable<Boolean>) () -> {
      event.getUsers().forEach(user -> {
        if (logger.isDebugEnabled()) {
          logger.debug("Nick:[{}] Hostmask:[{}]", user.getNick(), user.getHostmask());
        }
      });
      return true;
    });
  }

  @Override
  public void onWhois(WhoisEvent event) {
    logger.info("Nick:[{}]", event.getNick());
  }

  @Override
  public void onPing(PingEvent event) {
    Objects.requireNonNull(event.getUser()).send().message("PONG");
  }

  @Override
  public void onTime(TimeEvent event) {
    String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    Objects.requireNonNull(event.getUser()).send().message(now);
  }

  @Override
  public void onUnknown(UnknownEvent event) throws Exception {

    UnknownEventMessageResolver resolver = UnknownEventMessageResolver.Builder.anUnknownEventMessageResolver()
        .withMessage(event.getLine()).build();
    if (logger.isDebugEnabled()) {
      logger.debug("TYPE:[{}]", resolver.getEventMessage());
    }
    switch (resolver.getEventMessage()) {
      case "USERINFO":
        event.getBot().send().message(resolver.getUser(), "I`m just a bot.");
        break;
      default:
        break;
    }
  }
}
