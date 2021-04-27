package br.ufes.inf.nemo.dev.cditravel.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import br.ufes.inf.nemo.dev.util.PersistentObjectImpl;

@Entity
public class TourPackage extends PersistentObjectImpl {
  private static final long serialVersionUID = 1L;

  @NotNull
  @Size(max = 50)
  private String name;

  @Temporal(TemporalType.DATE)
  private Date begin;

  @Temporal(TemporalType.DATE)
  private Date end;

  @Lob
  private String description;

  @NotNull
  private BigDecimal price;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getEnd() {
    return end;
  }

  public void setEnd(Date end) {
    this.end = end;
  }

  public Date getBegin() {
    return begin;
  }

  public void setBegin(Date begin) {
    this.begin = begin;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return name;
  }
}
