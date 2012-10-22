package br.ufla.dcc.siscob.model.persistence.dao.hibernate;

import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.ufla.dcc.siscob.model.domain.entity.ItemEmprestimo;
import br.ufla.dcc.siscob.model.domain.entity.Publicacao;
import br.ufla.dcc.siscob.model.domain.entity.Usuario;
import br.ufla.dcc.siscob.model.persistence.dao.ItemEmprestimoDAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAOImplementation;
import br.ufla.lemaf.commons.model.persistence.dao.hibernate.HibernateDAO;

@Named
@DAO( implementation = DAOImplementation.HIBERNATE )
public class ItemEmprestimoHibernateDAO extends HibernateDAO<ItemEmprestimo, Long> implements ItemEmprestimoDAO {
  
  public Boolean hasEmprestimo(Publicacao publicacao) {
    try {
      Query query = this.entityManager.createNativeQuery("SELECT * FROM siscob.itememprestimo i, siscob.emprestimo e WHERE e.id = i.id_emprestimo AND e.ativo = :ativo AND i.datadadevolucao IS NULL AND i.id_publicacao = :id AND i.id_emprestimo IS NOT NULL", ItemEmprestimo.class);
      ItemEmprestimo item = (ItemEmprestimo) query.setMaxResults(1).setParameter("id", publicacao.getId()).setParameter("ativo", true).getSingleResult();
      return item==null ? false : true;
    } catch (NoResultException e) {
      return false;
    }
    
  }
  
  public Boolean hasEmprestimo(Usuario usuario) {
    try {
      Query query = this.entityManager.createNativeQuery("SELECT * FROM siscob.emprestimo e, siscob.itememprestimo i WHERE i.id_emprestimo = e.id AND e.ativo = :ativo AND i.datadadevolucao IS NULL AND e.id_usuario = :id");
      Object exists = (Object) query.setMaxResults(1).setParameter("id", usuario.getId()).setParameter("ativo", true).getSingleResult();
      return exists==null ? false : true;
    } catch (NoResultException e) {
      return false;
    }
    
  }
  
}
