package br.ufes.inf.nemo.dev.util;

import java.io.Serializable;

public interface DomainObject extends Serializable {
  String getUuid();
}
