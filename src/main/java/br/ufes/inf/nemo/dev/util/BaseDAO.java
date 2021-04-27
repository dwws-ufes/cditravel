package br.ufes.inf.nemo.dev.util;

import java.util.List;

public interface BaseDAO<T extends PersistentObject> {
  long retrieveCount();

  List<T> retrieveAll();

  List<T> retrieveSome(int[] interval);

  T retrieveById(Long id);

  T save(T object);

  void delete(T object);
}
