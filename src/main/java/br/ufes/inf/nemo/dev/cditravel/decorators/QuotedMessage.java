package br.ufes.inf.nemo.dev.cditravel.decorators;

import javax.annotation.Priority;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.interceptor.Interceptor;
import br.ufes.inf.nemo.dev.cditravel.beans.Message;

@Dependent
@Priority(Interceptor.Priority.APPLICATION)
@Decorator
public class QuotedMessage implements Message {
  @Inject
  @Delegate
  private Message message;

  @Override
  public String getMessage() {
    return "\"" + message.getMessage() + "\"";
  }
}
