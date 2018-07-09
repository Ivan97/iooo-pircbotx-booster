package tech.iooo.coco.handler;

import org.pircbotx.hooks.events.TopicEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.iooo.coco.commons.IrcEventListener;

/**
 * Created on 2018/7/6 下午2:48
 *
 * @author <a href="mailto:yangkizhang@gmail.com?subject=iooo-pircbotx-booster">Ivan97</a>
 */
@IrcEventListener
public class CommonEventListener extends BaseEventListener {

  private static final Logger logger = LoggerFactory.getLogger(CommonEventListener.class);

  @Override
  public void onTopic(TopicEvent event) throws Exception {
    String channelName = event.getChannel().getName();
    logger.info("channel:[{}] [{}]", channelName, event.getTopic());
  }
}
