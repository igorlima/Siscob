package br.ufla.dcc.siscob.model.persistence.dao;

import java.util.List;

import br.ufla.dcc.siscob.model.domain.entity.Emprestimo;
import br.ufla.lemaf.commons.model.persistence.dao.DAO;

public interface EmprestimoDAO extends DAO<Emprestimo, Long> {
  
  List<Emprestimo> retrieve();
  
}
