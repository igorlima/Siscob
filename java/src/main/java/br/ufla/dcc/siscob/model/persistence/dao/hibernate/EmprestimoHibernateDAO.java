package br.ufla.dcc.siscob.model.persistence.dao.hibernate;

import java.util.List;

import javax.inject.Named;
import javax.persistence.TypedQuery;

import br.ufla.dcc.siscob.model.domain.entity.Emprestimo;
import br.ufla.dcc.siscob.model.persistence.dao.EmprestimoDAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAOImplementation;
import br.ufla.lemaf.commons.model.persistence.dao.hibernate.HibernateDAO;

@Named
@DAO( implementation = DAOImplementation.HIBERNATE )
public class EmprestimoHibernateDAO extends HibernateDAO<Emprestimo, Long> implements EmprestimoDAO {
  
  @Override 
  public List<Emprestimo> retrieve(){
    TypedQuery<Emprestimo> query = this.entityManager.createQuery("from Emprestimo where ativo = :ativo", Emprestimo.class);
    query.setParameter("ativo", true);
    return query.getResultList();
  };
	
}
