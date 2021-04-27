package br.ufes.inf.nemo.dev.cditravel.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import br.ufes.inf.nemo.dev.cditravel.domain.TourPackage;
import br.ufes.inf.nemo.dev.cditravel.events.SearchEvent;
import br.ufes.inf.nemo.dev.cditravel.persistence.TourPackageDAO;

@ConversationScoped
@Named
public class SearchPackages implements Serializable {
  @EJB
  private TourPackageDAO tourPackageDAO;

  @Inject
  private Conversation conversation;

  @Inject
  private Event<SearchEvent> searchEvent;

  private String name;

  private List<TourPackage> searchResults;

  private Map<String, TourPackage> selectedPackages = new HashMap<String, TourPackage>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<TourPackage> getSearchResults() {
    return searchResults;
  }

  public List<TourPackage> getSelectedPackages() {
    return new ArrayList<TourPackage>(selectedPackages.values());
  }

  public String begin() {
    conversation.begin();
    return "/searchPackages.xhtml";
  }

  public String search() {
    searchEvent.fire(new SearchEvent(name));
    searchResults = tourPackageDAO.findByName(name);
    return null;
  }

  public String add(String sID) {
    Long id = Long.parseLong(sID);
    TourPackage pacote = tourPackageDAO.retrieveById(id);
    selectedPackages.put(sID, pacote);
    return null;
  }

  public String remove(String sID) {
    selectedPackages.remove(sID);
    return null;
  }
}
