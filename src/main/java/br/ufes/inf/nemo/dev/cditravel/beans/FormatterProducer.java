package br.ufes.inf.nemo.dev.cditravel.beans;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import br.ufes.inf.nemo.dev.cditravel.qualifiers.CurrencyFormatter;
import br.ufes.inf.nemo.dev.cditravel.qualifiers.IntegerFormatter;

@ApplicationScoped
public class FormatterProducer {
  private static final Locale LOCALE = new Locale("en", "US");

  @Produces
  private static final DateFormat DF = new SimpleDateFormat("MM/dd/yyyy");

  @Produces
  @CurrencyFormatter
  private final NumberFormat CF = NumberFormat.getCurrencyInstance(LOCALE);

  @Produces
  @IntegerFormatter
  private final NumberFormat IF = NumberFormat.getIntegerInstance(LOCALE);

  public FormatterProducer() {
    IF.setMinimumIntegerDigits(2);
  }
}
