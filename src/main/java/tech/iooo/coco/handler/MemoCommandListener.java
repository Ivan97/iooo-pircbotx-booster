package tech.iooo.coco.handler;

import java.util.Objects;
import org.pircbotx.hooks.types.GenericMessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import tech.iooo.coco.commons.IoooListenerAdapter;
import tech.iooo.coco.commons.IrcEventListener;
import tech.iooo.coco.commons.PircbotProperties;
import tech.iooo.coco.configuration.IrcConstants;

/**
 * Created on 2018/7/17 下午5:26
 *
 * @author <a href="mailto:yangkizhang@gmail.com?subject=iooo-pircbotx-booster">Ivan97</a>
 */
@IrcEventListener(command = "memo")
public class MemoCommandListener extends IoooListenerAdapter {

  @Autowired
  private PircbotProperties pircbotProperties;

  @Override
  public void onGenericMessage(GenericMessageEvent event) throws Exception {
    String text = resolveMessage(event.getMessage());
    if (Objects.equals(text.toLowerCase(), "help")) {
      event.respond(pircbotProperties.getCommandPrefix() + "memo ${username} ${msg}");
    } else {
      event.getBot().send().message(IrcConstants.MEMO_SERV, "send " + text);
      event.respond("The memo has been successfully sent.");
    }
  }
}
