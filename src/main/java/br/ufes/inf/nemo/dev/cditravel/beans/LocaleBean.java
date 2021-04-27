package br.ufes.inf.nemo.dev.cditravel.beans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LocaleBean implements Serializable {
  private final Locale LOCALE = new Locale("en", "US");

  private final DateFormat DF = new SimpleDateFormat("MM/dd/yyyy");

  private final NumberFormat CF = NumberFormat.getCurrencyInstance(LOCALE);

  public DateFormat getDateFormatter() {
    return DF;
  }

  public NumberFormat getCurrencyFormatter() {
    return CF;
  }
}
