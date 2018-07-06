package tech.iooo.coco;

import java.io.IOException;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/7/5 上午8:57
 *
 * @author <a href="mailto:yangkizhang@gmail.com?subject=iooo-pircbotx-booster">Ivan97</a>
 */
@Component
public class PircotxLifecycle implements SmartLifecycle {

  private static final Logger logger = LoggerFactory.getLogger(PircotxLifecycle.class);
  @Autowired
  private PircBotX ircBot;
  private boolean isRunning;

  @Override
  public boolean isAutoStartup() {
    return true;
  }

  @Override
  public void stop(Runnable runnable) {
    ircBot.stopBotReconnect();
    runnable.run();
    this.isRunning = false;
  }

  @Override
  public void start() {
    try {
      ircBot.startBot();
    } catch (IOException | IrcException e) {
      e.printStackTrace();
    }
    logger.info("PircbotX started");
    this.isRunning = true;
  }

  @Override
  public void stop() {
    ircBot.stopBotReconnect();
    this.isRunning = false;
  }

  @Override
  public boolean isRunning() {
    return this.isRunning;
  }

  @Override
  public int getPhase() {
    return Integer.MAX_VALUE;
  }
}
