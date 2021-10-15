package br.ufes.inf.nemo.dev.cditravel.beans;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.New;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import br.ufes.inf.nemo.dev.cditravel.domain.TourPackage;
import br.ufes.inf.nemo.dev.cditravel.persistence.TourPackageDAO;

@Stateful
@LocalBean
@Model
public class AddPackage {
  private static final Logger logger = Logger.getLogger(AddPackage.class.getCanonicalName());

  @EJB
  private TourPackageDAO tourPackageDAO;

  @Inject
  private LocaleBean loc;

  @Inject
  @New
  private LocaleBean loc2;

  private List<TourPackage> packages;

  private TourPackage pack = new TourPackage();

  @Inject
  void loadPackages() {
    packages = tourPackageDAO.retrieveAll();
    logger.log(Level.INFO, "Loading tour packages: {0} packages loaded", packages.size());

    // Shows that loc and loc2 are two different objects.
    System.out.println("########## loc = " + loc + "; loc2 = " + loc2);
  }

  public List<TourPackage> getPackages() {
    return packages;
  }

  public TourPackage getPack() {
    return pack;
  }

  public String add() {
    DateFormat df = loc.getDateFormatter();
    NumberFormat cf = loc.getCurrencyFormatter();

    logger.log(Level.INFO, "Adding tour package: [name= {0}; begin = {1}; end = {2}; price = {3}]",
        new Object[] {pack.getName(), df.format(pack.getBegin()), df.format(pack.getEnd()),
            cf.format(pack.getPrice())});
    tourPackageDAO.save(pack);
    packages.add(pack);

    FacesContext context = FacesContext.getCurrentInstance();
    context.addMessage(null,
        new FacesMessage("Tour Package \"" + pack.getName() + "\" added successfully!"));
    pack = new TourPackage();
    return null;
  }
  
public void suggestDescription() {
  String name = pack.getName();
  if (name != null && name.length() > 3) {
    String query = "PREFIX dbo: <http://dbpedia.org/ontology/>\n" + 
        "PREFIX dbp: <http://dbpedia.org/property/>\n" + 
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
        "SELECT ?desc\n" + 
        "WHERE {\n" + 
        "  ?uri a dbo:City ;\n" + 
        "          dbp:name \"Rio de Janeiro\"@en ;\n" + 
        "          rdfs:comment ?desc .\n" + 
        "FILTER (langMatches(lang(?desc), \"EN\"))\n" + 
        "}";
    
    QueryExecution queryExecution = 
        QueryExecutionFactory.sparqlService("https://dbpedia.org/sparql", query);
    ResultSet results = queryExecution.execSelect();
    
    if (results.hasNext()) {
      QuerySolution querySolution = results.next();
      Literal literal = querySolution.getLiteral("desc");
      pack.setDescription("" + literal.getValue());
    }
  }
}
}





