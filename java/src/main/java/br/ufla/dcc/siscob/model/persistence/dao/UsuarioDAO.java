package br.ufla.dcc.siscob.model.persistence.dao;

import java.util.List;

import br.ufla.dcc.siscob.model.domain.entity.Usuario;
import br.ufla.lemaf.commons.model.persistence.dao.DAO;

public interface UsuarioDAO extends DAO<Usuario, Long> {
  
  List<Usuario> retrieve();
  List<Usuario> listaUsuarioPorNome(String nome);
  Usuario buscaPorCpf(String cpf);
  
}
