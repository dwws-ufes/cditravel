package br.ufes.inf.nemo.dev.cditravel.persistence;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.ufes.inf.nemo.dev.cditravel.domain.TourPackage;
import br.ufes.inf.nemo.dev.cditravel.domain.TourPackage_;
import br.ufes.inf.nemo.dev.util.BaseJPADAO;

@Stateless
public class TourPackageJPADAO extends BaseJPADAO<TourPackage> implements TourPackageDAO {
  @PersistenceContext
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  @Override
  protected Class<TourPackage> getDomainClass() {
    return TourPackage.class;
  }

  @Override
  public List<TourPackage> findByName(String name) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<TourPackage> cq = cb.createQuery(TourPackage.class);
    Root<TourPackage> root = cq.from(TourPackage.class);
    cq.where(cb.like(cb.lower(root.get(TourPackage_.name)), "%" + name.toLowerCase() + "%"));
    return em.createQuery(cq).getResultList();
  }
}
