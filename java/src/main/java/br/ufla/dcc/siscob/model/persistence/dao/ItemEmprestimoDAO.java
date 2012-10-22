package br.ufla.dcc.siscob.model.persistence.dao;

import br.ufla.dcc.siscob.model.domain.entity.ItemEmprestimo;
import br.ufla.dcc.siscob.model.domain.entity.Publicacao;
import br.ufla.dcc.siscob.model.domain.entity.Usuario;
import br.ufla.lemaf.commons.model.persistence.dao.DAO;

public interface ItemEmprestimoDAO extends DAO<ItemEmprestimo, Long> {
  Boolean hasEmprestimo(Publicacao publicacao);
  Boolean hasEmprestimo(Usuario usuario);
}
