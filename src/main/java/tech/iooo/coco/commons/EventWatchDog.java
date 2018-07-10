package tech.iooo.coco.commons;

import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.events.ActionEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

/**
 * Created on 2018/7/10 上午9:41
 *
 * @author <a href="mailto:yangkizhang@gmail.com?subject=iooo-pircbotx-booster">Ivan97</a>
 */
public class EventWatchDog {

  public static EventType sniff(Event event) {
    if (event instanceof ActionEvent) {
      return EventType.ACTION;
    } else if (event instanceof MessageEvent || event instanceof PrivateMessageEvent) {
      return EventType.MESSAGE;
    } else {
      return EventType.COMMON_EVENT;
    }
  }

  public enum EventType {
    MESSAGE, COMMON_EVENT, ACTION
  }
}
