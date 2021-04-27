package br.ufes.inf.nemo.dev.cditravel.beans;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

@Model
public class SendMessage {
  private String name;
  private String email;
  private String message;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String send() {
    /* Code that sends the message via e-mail (not implemented). */

    // Stores the bean in the FLASH context.
    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    flash.put("sendMessage", this);

    // Redirects to the thank you page.
    return "thanksForYourMessage.xhtml?faces-redirect=true";
  }
}
