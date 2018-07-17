package tech.iooo.coco.handler;

import com.github.jarod.qqwry.IPZone;
import org.pircbotx.hooks.types.GenericMessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import tech.iooo.coco.commons.IoooListenerAdapter;
import tech.iooo.coco.commons.IrcEventListener;
import tech.iooo.coco.service.QQWryService;

/**
 * Created on 2018/7/17 下午2:12
 *
 * @author <a href="mailto:yangkizhang@gmail.com?subject=iooo-pircbotx-booster">Ivan97</a>
 */
@IrcEventListener(command = "ip")
public class IpCommandListener extends IoooListenerAdapter {

  @Autowired
  private QQWryService wryService;

  @Override
  public void onGenericMessage(GenericMessageEvent event) throws Exception {
    String ip = resolveMessage(event.getMessage());
    IPZone ipZone = wryService.findIp(ip);
    event.respond(String.format("[%s|%s]", ip, ipZone.toString()));
  }
}
