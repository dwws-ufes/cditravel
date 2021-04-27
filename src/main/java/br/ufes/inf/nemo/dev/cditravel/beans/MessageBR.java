package br.ufes.inf.nemo.dev.cditravel.beans;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MessageBR implements Message {
  @Override
  public String getMessage() {
    return "A CDI Travel é uma agência com muita tradição no mercado, etc. etc...";
  }
}
