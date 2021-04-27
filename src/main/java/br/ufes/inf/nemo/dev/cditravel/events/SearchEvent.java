package br.ufes.inf.nemo.dev.cditravel.events;

public class SearchEvent {
  private String name;

  public SearchEvent(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
