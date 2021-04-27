package br.ufes.inf.nemo.dev.cditravel.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class ListConversations implements Serializable {
  private List<String> conversationIds = new ArrayList<String>();

  public void add(Conversation conversation) {
    conversationIds.add(conversation.getId());
  }

  public void remove(Conversation conversation) {
    conversationIds.remove(conversation.getId());
  }

  public List<String> getList() {
    return conversationIds;
  }
}
