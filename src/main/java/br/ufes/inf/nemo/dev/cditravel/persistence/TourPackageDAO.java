package br.ufes.inf.nemo.dev.cditravel.persistence;

import java.util.List;
import javax.ejb.Local;
import br.ufes.inf.nemo.dev.cditravel.domain.TourPackage;
import br.ufes.inf.nemo.dev.util.BaseDAO;

@Local
public interface TourPackageDAO extends BaseDAO<TourPackage> {
  List<TourPackage> findByName(String name);
}
