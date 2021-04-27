package br.ufes.inf.nemo.dev.cditravel.beans;

import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named("cvmg")
public class ConversationManager implements Serializable {
  @Inject
  private Conversation conversation;

  @Inject
  private ListConversations list;

  public Conversation getConversation() {
    return conversation;
  }

  public String beginConversation() {
    conversation.begin();
    list.add(conversation);
    return null;
  }

  public String endConversation() {
    list.remove(conversation);
    conversation.end();
    return null;
  }
}
