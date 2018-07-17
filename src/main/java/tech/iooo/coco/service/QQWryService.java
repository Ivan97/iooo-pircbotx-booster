package tech.iooo.coco.service;

import com.github.jarod.qqwry.IPZone;
import com.github.jarod.qqwry.QQWry;
import java.nio.file.Paths;
import java.util.Objects;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.iooo.coco.commons.ApplicationProperties;

/**
 * Created on 2018/7/17 下午2:06
 *
 * @author <a href="mailto:yangkizhang@gmail.com?subject=iooo-pircbotx-booster">Ivan97</a>
 */
@Service
public class QQWryService implements InitializingBean {

  @Autowired
  private ApplicationProperties applicationProperties;

  private QQWry qqwry;

  public IPZone findIp(String ip) {
    return qqwry.findIP(ip);
  }


  @Override
  public void afterPropertiesSet() throws Exception {
    String path = String.format("data/qqwry.dat/%s/qqwry.dat", applicationProperties.getWryDat().getUpdateTime());
    this.qqwry = new QQWry(
        Paths.get(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(path)).toURI()));
  }
}
