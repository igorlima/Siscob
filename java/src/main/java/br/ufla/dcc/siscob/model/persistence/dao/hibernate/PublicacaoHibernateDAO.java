package br.ufla.dcc.siscob.model.persistence.dao.hibernate;

import java.util.List;

import javax.inject.Named;
import javax.persistence.TypedQuery;

import br.ufla.dcc.siscob.model.domain.entity.Publicacao;
import br.ufla.dcc.siscob.model.persistence.dao.PublicacaoDAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAOImplementation;
import br.ufla.lemaf.commons.model.persistence.dao.hibernate.HibernateDAO;

@Named
@DAO( implementation = DAOImplementation.HIBERNATE )
public class PublicacaoHibernateDAO extends HibernateDAO<Publicacao, Long> implements PublicacaoDAO {
	
	public List<Publicacao> buscarPorAutores(String autores) {
	  TypedQuery<Publicacao> query = this.entityManager.createQuery( "select * from publicacao where autores like :autores", Publicacao.class );
		query.setParameter("autores", autores);  
		return query.getResultList();
	}
	
	public List<Publicacao> buscarPorTitulo(String titulo) {
	  TypedQuery<Publicacao> query = this.entityManager.createQuery( "select * from publicacao where titulo like :titulo", Publicacao.class );
		query.setParameter("titulo", titulo);  
		return query.getResultList();  
	}
	
}
