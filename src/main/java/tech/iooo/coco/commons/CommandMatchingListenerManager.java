package tech.iooo.coco.commons;

import com.google.common.base.Strings;
import java.util.concurrent.ExecutorService;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.events.ActionEvent;
import org.pircbotx.hooks.managers.ThreadedListenerManager;
import org.pircbotx.hooks.types.GenericMessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/7/5 下午3:28
 *
 * @author <a href="mailto:yangkizhang@gmail.com?subject=iooo-pircbotx-booster">Ivan97</a>
 */
@Component
public class CommandMatchingListenerManager extends ThreadedListenerManager implements InitializingBean {

  private static final Logger logger = LoggerFactory.getLogger(CommandMatchingListenerManager.class);

  @Autowired
  private ExecutorService executorService;
  @Autowired
  private PircbotProperties pircbotProperties;
  @Autowired
  private ListenerContextService listenerContextService;

  @Override
  public void onEvent(Event event) {
    switch (EventWatchDog.sniff(event)) {
      case MESSAGE: {
        String command = getCommand(((GenericMessageEvent) event).getMessage());
        if (!Strings.isNullOrEmpty(command)) {
          Listener commandListeners = listenerContextService.getCommandListeners().get(command);
          if (logger.isDebugEnabled()) {
            logger.debug("redirect to listener on command [{}]", command);
          }
          submitEvent(pool, commandListeners, event);
        }
        break;
      }
      case ACTION: {
        if (logger.isDebugEnabled()) {
          logger.debug("redirect to listener on action [{}]", ((ActionEvent) event).getAction());
        }
        listenerContextService.getActionListeners().forEach(listener -> submitEvent(pool, listener, event));
        break;
      }
      case COMMON_EVENT: {
        if (logger.isDebugEnabled()) {
          logger.debug("redirect to listener on event [{}]", event.getClass().getSimpleName());
        }
        listenerContextService.getEventListeners().forEach(listener -> submitEvent(pool, listener, event));
        break;
      }
      default:
        break;
    }
  }


  @Bean
  public ThreadedListenerManager listenerManager() {
    ThreadedListenerManager listenerManager = new ThreadedListenerManager();
    listenerContextService.listeners().forEach(listenerManager::addListener);
    return listenerManager;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    this.pool = executorService;
  }

  private String getCommand(String message) {
    if (!Strings.isNullOrEmpty(message)) {
      String command = message.split(" ")[0].replaceFirst(pircbotProperties.getCommandPrefix(), "");
      if (listenerContextService.commands().contains(command)) {
        return command;
      } else {
        return null;
      }
    } else {
      return null;
    }
  }
}
