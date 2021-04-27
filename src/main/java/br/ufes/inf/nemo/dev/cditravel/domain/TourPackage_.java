package br.ufes.inf.nemo.dev.cditravel.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TourPackage.class)
public class TourPackage_ {
  public static volatile SingularAttribute<TourPackage, String> name;
  public static volatile SingularAttribute<TourPackage, Date> begin;
  public static volatile SingularAttribute<TourPackage, Date> end;
  public static volatile SingularAttribute<TourPackage, String> description;
  public static volatile SingularAttribute<TourPackage, BigDecimal> price;
}
