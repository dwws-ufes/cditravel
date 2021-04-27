package br.ufes.inf.nemo.dev.util;


public interface PersistentObject extends DomainObject {
  Long getId();

  Long getVersion();

  boolean isPersistent();
}
