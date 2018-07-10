package tech.iooo.coco.utils;

import lombok.Getter;
import lombok.Setter;
import ru.lanwen.verbalregex.VerbalExpression;

/**
 * Created on 2018/7/10 下午4:17
 *
 * @author <a href="mailto:yangkizhang@gmail.com?subject=iooo-pircbotx-booster">Ivan97</a>
 */
public class UnknownEventMessageResolver {

  private static VerbalExpression messageExpression = VerbalExpression.regex()
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
  @Getter
  private static boolean match = false;
  @Getter
  @Setter
  private String line;


  private UnknownEventMessageResolver() {
  }

  public String getUser() {
    return match ? messageExpression.getText(this.line, 1) : "";
  }

  public String getHostMask() {
    return match ? messageExpression.getText(this.line, 2) : "";
  }

  public String getMessageType() {
    return match ? messageExpression.getText(this.line, 3) : "";
  }

  public String getBotName() {
    return match ? messageExpression.getText(this.line, 4) : "";
  }

  public String getEventMessage() {
    return match ? messageExpression.getText(this.line, 5).trim() : "";
  }

  public String user() {
    return "";
  }

  public static final class Builder {

    private String message;

    private Builder() {
    }

    public static Builder anUnknownEventMessageResolver() {
      return new Builder();
    }

    public Builder withMessage(String message) {
      this.message = message;
      return this;
    }

    public Builder but() {
      return anUnknownEventMessageResolver().withMessage(message);
    }

    public UnknownEventMessageResolver build() {
      UnknownEventMessageResolver unknownEventMessageResolver = new UnknownEventMessageResolver();
      unknownEventMessageResolver.setLine(message);
      match = messageExpression.test(message);
      return unknownEventMessageResolver;
    }
  }
}
