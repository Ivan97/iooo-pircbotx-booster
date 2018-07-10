package tech.iooo.coco.handler;

import java.util.Objects;
import org.pircbotx.hooks.events.ActionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.iooo.coco.commons.IrcEventListener;

/**
 * Created on 2018/7/6 下午2:48
 *
 * @author <a href="mailto:yangkizhang@gmail.com?subject=iooo-pircbotx-booster">Ivan97</a>
 */
@IrcEventListener(action = true)
public class CommonActionListener extends BaseEventListener {

  private static final Logger logger = LoggerFactory.getLogger(CommonActionListener.class);

  @Override
  public void onAction(ActionEvent event) throws Exception {
    switch (event.getAction()) {
      case "whois":
        if (logger.isDebugEnabled()) {
          logger.debug("on whois action");
          Objects.requireNonNull(event.getUser()).send().message("hello");
        }
        break;
      default:
        break;
    }
  }
}
