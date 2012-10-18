package br.ufla.dcc.siscob.model.persistence.dao.hibernate;

import java.util.List;

import javax.inject.Named;
import javax.persistence.TypedQuery;

import br.ufla.dcc.siscob.model.domain.entity.Usuario;
import br.ufla.dcc.siscob.model.persistence.dao.UsuarioDAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAOImplementation;
import br.ufla.lemaf.commons.model.persistence.dao.hibernate.HibernateDAO;

@Named
@DAO( implementation = DAOImplementation.HIBERNATE )
public class UsuarioHibernateDAO extends HibernateDAO<Usuario, Long> implements UsuarioDAO {
	
	public List<Usuario> listaUsuarioPorNome(String nome) {
	  TypedQuery<Usuario> query = this.entityManager.createQuery( "from Usuario where nome like :nome", Usuario.class);
	  query.setParameter("p", nome);
	  return query.getResultList();
	}
	
	public Usuario buscaPorCpf(String cpf) {
	  TypedQuery<Usuario> query = this.entityManager.createQuery( "from Usuario where cpf like :cpf", Usuario.class);
	  query.setParameter("cpf", cpf);
	  query.setMaxResults(1);
	  return query.getSingleResult();
	}
	
}
