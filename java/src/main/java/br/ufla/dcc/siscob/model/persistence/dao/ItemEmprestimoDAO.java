package br.ufla.dcc.siscob.model.persistence.dao;

import java.util.List;

import br.ufla.dcc.siscob.model.domain.entity.ItemEmprestimo;
import br.ufla.lemaf.commons.model.persistence.dao.DAO;

public interface ItemEmprestimoDAO extends DAO<ItemEmprestimo, Long> {
  
  List<ItemEmprestimo> retrieve();
  
}
