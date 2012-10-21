package br.ufla.dcc.siscob.model.persistence.dao;

import java.util.List;

import br.ufla.dcc.siscob.model.domain.entity.Publicacao;
import br.ufla.lemaf.commons.model.persistence.dao.DAO;

public interface PublicacaoDAO extends DAO<Publicacao, Long> {
  
  List<Publicacao> retrieve();
  List<Publicacao> buscarPorAutores(String autores);
  List<Publicacao> buscarPorTitulo(String titulo);
  void evict(Publicacao publicacao);

}
