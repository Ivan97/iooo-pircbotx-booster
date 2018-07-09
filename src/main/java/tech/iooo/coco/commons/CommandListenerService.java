package tech.iooo.coco.commons;

import com.google.common.base.Strings;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import org.pircbotx.hooks.Listener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Created on 2018/7/5 下午5:40
 *
 * @author <a href="mailto:yangkizhang@gmail.com?subject=iooo-pircbotx-booster">Ivan97</a>
 */
@Service
public class CommandListenerService implements ApplicationContextAware, InitializingBean {

  private ApplicationContext applicationContext;

  @Getter
  private HashBiMap<String, Listener> listenersWithCommands = HashBiMap.create();
  @Getter
  private List<Listener> listenersWithoutCommands = Lists.newArrayList();

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  public Iterable<Listener> listeners() {
    return this.listenersWithCommands.values();
  }

  public Set<String> commands() {
    return this.listenersWithCommands.keySet();
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    Map<String, Object> eventListeners = applicationContext.getBeansWithAnnotation(IrcEventListener.class);

    eventListeners.forEach((name, listener) -> {
      IrcEventListener annotation = applicationContext.findAnnotationOnBean(name, IrcEventListener.class);
      if (Strings.isNullOrEmpty(annotation.command())) {
        listenersWithoutCommands.add((Listener) listener);
      } else {
        listenersWithCommands.put(annotation.command(), (Listener) listener);
      }
    });

  }
}
