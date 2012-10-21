package br.ufla.dcc.siscob.model.persistence.dao.hibernate;

import java.util.List;

import javax.inject.Named;
import javax.persistence.TypedQuery;

import br.ufla.dcc.siscob.model.domain.entity.ItemEmprestimo;
import br.ufla.dcc.siscob.model.persistence.dao.ItemEmprestimoDAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAOImplementation;
import br.ufla.lemaf.commons.model.persistence.dao.hibernate.HibernateDAO;

@Named
@DAO( implementation = DAOImplementation.HIBERNATE )
public class ItemEmprestimoHibernateDAO extends HibernateDAO<ItemEmprestimo, Long> implements ItemEmprestimoDAO {
  
  @Override 
  public List<ItemEmprestimo> retrieve(){
    TypedQuery<ItemEmprestimo> query = this.entityManager.createQuery("from ItemEmprestimo where ativo = :ativo", ItemEmprestimo.class);
    query.setParameter("ativo", true);
    return query.getResultList();
  };
	
}
