package tech.iooo.coco.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanwen.verbalregex.VerbalExpression;

/**
 * Created on 2018/7/10 下午4:35
 *
 * @author <a href="mailto:yangkizhang@gmail.com?subject=iooo-pircbotx-booster">Ivan97</a>
 */
@RunWith(JUnit4.class)
public class UnknownEventMessageResolverTest {

  private static final Logger logger = LoggerFactory.getLogger(UnknownEventMessageResolverTest.class);
  String message = ":Ivan97!~Ivan97@unaffiliated/ivan97 PRIVMSG SimpleBot :\u0001USERINFO\u0001";

  VerbalExpression messageExpression = VerbalExpression.regex()
      .startOfLine().then(":")
      //user
      .capture().anything().endCapture()
      .then("!~")
      //hostmask
      .capture().anything().endCapture()
      .space().atLeast(1)
      //msg type
      .capture().anything().endCapture()
      .space().atLeast(1)
      //bot name
      .capture().anything().endCapture()
      .space().atLeast(1)
      .then(":")
      //event line
      .capture().anything().endCapture()
      .endOfLine().build();

  private UnknownEventMessageResolver resolver = UnknownEventMessageResolver.Builder
      .anUnknownEventMessageResolver().withMessage(message).build();

  @Test
  public void user() {
    logger.info("User:{}", resolver.getUser());
    logger.info("BotName:{}", resolver.getBotName());
    logger.info("HostMask:{}", resolver.getHostMask());
    logger.info("MessageType:{}", resolver.getMessageType());
    logger.info("EventMessage:{}", resolver.getEventMessage());
  }
}
