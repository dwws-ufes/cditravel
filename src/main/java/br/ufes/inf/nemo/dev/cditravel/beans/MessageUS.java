package br.ufes.inf.nemo.dev.cditravel.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@Alternative
@ApplicationScoped
public class MessageUS implements Message {
  @Override
  public String getMessage() {
    return "CDI Travel is a travel agency with years of experience in etc. etc...";
  }
}
