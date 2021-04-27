package br.ufes.inf.nemo.dev.util;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class PersistentObjectImpl extends DomainObjectImpl implements PersistentObject {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Version
  @Column(nullable = false)
  private Long version;

  @Override
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public Long getVersion() {
    return version;
  }

  protected void setVersion(Long version) {
    this.version = version;
  }

  @Override
  public boolean isPersistent() {
    return (id != null);
  }

  @Override
  public String toString() {
    return "Instance of " + getClass().getName() + " (id: " + id + "; uuid: " + uuid + ")";
  }
}
