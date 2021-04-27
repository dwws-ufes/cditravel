package br.ufes.inf.nemo.dev.cditravel.beans;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
@Named
public class ShowMessage implements Serializable {
  @Inject
  private Message message;

  public String getMessage() {
    return message.getMessage();
  }
}
