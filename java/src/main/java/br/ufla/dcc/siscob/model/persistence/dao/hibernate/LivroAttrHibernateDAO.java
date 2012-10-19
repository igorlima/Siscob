package br.ufla.dcc.siscob.model.persistence.dao.hibernate;

import javax.inject.Named;

import br.ufla.dcc.siscob.model.domain.entity.LivroAttr;
import br.ufla.dcc.siscob.model.persistence.dao.LivroAttrDAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAOImplementation;
import br.ufla.lemaf.commons.model.persistence.dao.hibernate.HibernateDAO;

@Named
@DAO( implementation = DAOImplementation.HIBERNATE )
public class LivroAttrHibernateDAO extends HibernateDAO<LivroAttr, Long> implements LivroAttrDAO {
	
}
