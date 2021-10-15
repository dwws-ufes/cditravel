package br.ufes.inf.nemo.dev.cditravel.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import br.ufes.inf.nemo.dev.cditravel.domain.TourPackage;
import br.ufes.inf.nemo.dev.cditravel.persistence.TourPackageDAO;

@WebServlet(urlPatterns = {"/data/tourpackages"})
public class ListPackagesInRdfServlet extends HttpServlet {
  private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
  
  @EJB
  private TourPackageDAO tourPackageDAO;
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    
    resp.setContentType("text/xml");
    
    List<TourPackage> packs = tourPackageDAO.retrieveAll();
    
    Model model = ModelFactory.createDefaultModel();

    // Namespaces.
    String myNS = "http://localhost:8080/cditravel/data/TourPackage/";
    String grNS = "http://purl.org/goodrelations/v1#";
    String scNS = "http://schema.org/";
    String ctNS = "http://localhost:8080/cditravel/vocab/cditravel.owl#";
    model.setNsPrefix("gr", grNS);
    model.setNsPrefix("schema", scNS);
    model.setNsPrefix("cditravel", ctNS);
    
    // Classes do vocabulário.
    Resource grOffering = ResourceFactory.createResource(grNS + "Offering");
    Resource grPriceSpecification = ResourceFactory.createResource(grNS + "PriceSpecification");
    Resource scProduct = ResourceFactory.createResource(scNS + "Product");
    Resource ctTourPackage = ResourceFactory.createResource(ctNS + "TourPackage");
    
    // Propriedades (predicados) do vocabulário.
    Property gravailabilityStarts = ResourceFactory.createProperty(grNS + "availabilityStarts");
    Property gravailabilityEnds = ResourceFactory.createProperty(grNS + "availabilityEnds");
    Property grhasPriceSpecification = ResourceFactory.createProperty(grNS + "hasPriceSpecification");
    Property grhasCurrencyValue = ResourceFactory.createProperty(grNS + "hasCurrencyValue");
    
    // Produzir o modelo em memória.
    for (TourPackage pack : packs) {
      model.createResource(myNS + pack.getId())
        .addProperty(RDF.type, grOffering)
        .addProperty(RDF.type, scProduct)
        .addProperty(RDF.type,  ctTourPackage)
        .addProperty(RDFS.label, pack.getName())
        .addProperty(RDFS.comment, pack.getDescription())
        .addLiteral(gravailabilityStarts, 
            ResourceFactory.createTypedLiteral(df.format(pack.getBegin()), XSDDatatype.XSDdateTime))
        .addLiteral(gravailabilityEnds, 
            ResourceFactory.createTypedLiteral(df.format(pack.getEnd()), XSDDatatype.XSDdateTime))
        .addProperty(grhasPriceSpecification, model.createResource()
            .addProperty(RDF.type, grPriceSpecification)
            .addLiteral(grhasCurrencyValue, pack.getPrice().floatValue())
        );
    }
    
    // Serializa e envia para o navegador.
    try (PrintWriter out = resp.getWriter()) {
      model.write(out, "RDF/XML");
    }
  }
}




