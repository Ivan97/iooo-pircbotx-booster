package tech.iooo.coco.commons;

import com.google.common.base.Strings;
import org.pircbotx.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 2018/7/5 下午4:10
 *
 * @author <a href="mailto:yangkizhang@gmail.com?subject=iooo-pircbotx-booster">Ivan97</a>
 */

public class IoooListenerAdapter extends ListenerAdapter {

  @Autowired
  private PircbotProperties pircbotProperties;
  @Autowired
  private CommandListenerService commandListenerService;

  protected String resolveMessage(String message) {
    if (message.split(" ").length > 1) {
      String[] data = message.split(" ", 2);
      if (!Strings.isNullOrEmpty(getCommand(data))) {
        return data[1].trim();
      } else {
        return message;
      }
    } else {
      return message;
    }
  }

  protected String getCommand(String message) {
    if (message.split(" ").length > 1) {
      String[] data = message.split(" ", 2);
      return getCommand(data);
    } else {
      return message;
    }
  }

  private String getCommand(String[] data) {
    String command = data[0].replaceFirst(pircbotProperties.getCommandPrefix(), "").trim();
    if (commandListenerService.commands().contains(command)) {
      return command;
    } else {
      return "";
    }
  }
}
