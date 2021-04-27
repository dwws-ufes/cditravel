package br.ufes.inf.nemo.dev.cditravel.beans;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;
import br.ufes.inf.nemo.dev.cditravel.events.SearchEvent;

@SessionScoped
@Named
public class ShowNumberOfSearches implements Serializable {
  private static final Logger logger =
      Logger.getLogger(ShowNumberOfSearches.class.getCanonicalName());

  private int qty = 0;

  public int getQty() {
    return qty;
  }

  public void countSearch(@Observes SearchEvent event) {
    logger.log(Level.INFO, "Counting one more search. Visitor searched by name: {0}",
        event.getName());
    qty++;
  }
}
