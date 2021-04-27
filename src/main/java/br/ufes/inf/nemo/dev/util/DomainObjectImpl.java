package br.ufes.inf.nemo.dev.util;

import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DomainObjectImpl implements DomainObject {
  private static final long serialVersionUID = 1L;

  @Basic
  @Column(nullable = false, length = 40)
  protected String uuid;

  public DomainObjectImpl() {
    uuid = UUID.randomUUID().toString();
  }

  @Override
  public String getUuid() {
    return uuid;
  }

  protected void setUuid(String uuid) {
    this.uuid = uuid;
  }

  @Override
  public boolean equals(Object obj) {
    if ((obj == null) || (!getClass().equals(obj.getClass())))
      return false;
    DomainObjectImpl o = (DomainObjectImpl) obj;

    return uuid.equals(o.uuid);
  }

  @Override
  public int hashCode() {
    return uuid.hashCode();
  }

  public int compareTo(DomainObjectImpl o) {
    return uuid.compareTo(o.uuid);
  }
}
