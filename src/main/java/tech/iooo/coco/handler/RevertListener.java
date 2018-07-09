package tech.iooo.coco.handler;

import org.pircbotx.hooks.types.GenericMessageEvent;
import tech.iooo.coco.commons.IoooListenerAdapter;
import tech.iooo.coco.commons.IrcEventListener;

/**
 * Created on 2018/7/5 下午2:23
 *
 * @author <a href="mailto:yangkizhang@gmail.com?subject=iooo-pircbotx-booster">Ivan97</a>
 */
@IrcEventListener(command = "revert")
public class RevertListener extends IoooListenerAdapter {

  @Override
  public void onGenericMessage(GenericMessageEvent event) throws Exception {
    event.respond(getCommand(event.getMessage()) + "|" + new StringBuilder(resolveMessage(event.getMessage())).reverse()
        .toString());
  }
}
