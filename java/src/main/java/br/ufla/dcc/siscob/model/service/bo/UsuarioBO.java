package br.ufla.dcc.siscob.model.service.bo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.ufla.dcc.siscob.model.domain.entity.Usuario;
import br.ufla.dcc.siscob.model.persistence.dao.UsuarioDAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAOImplementation;
import br.ufla.lemaf.commons.model.service.to.MessageReturnTO;
import br.ufla.lemaf.commons.model.service.to.ObjectAndMessageReturnTO;
import br.ufla.lemaf.commons.model.service.to.ReturnTO;

@Named
public class UsuarioBO {

	@DAO( implementation = DAOImplementation.HIBERNATE )
	@Inject private UsuarioDAO dao;

	public ReturnTO salvar(Usuario usuario) {
    dao.createOrUpdate(usuario);
    return new MessageReturnTO();
  }
  
  public ReturnTO editar(Usuario usuario) {
    dao.createOrUpdate(usuario);
    return new MessageReturnTO();
  }
  
  public ReturnTO excluir(Usuario usuario) {
    dao.delete(usuario);
    return new MessageReturnTO();
  }
  
  public ReturnTO recuperar() {
    return new ObjectAndMessageReturnTO<List<Usuario>>(dao.retrieve());
  }
  
  public ReturnTO recuperar(Long id) {
    return new ObjectAndMessageReturnTO<Usuario>(dao.retrieve(id));
  }
  
  public ReturnTO recuperarPorNome(String nome) {
    return new ObjectAndMessageReturnTO<List<Usuario>>(dao.listaUsuarioPorNome(nome));
  }
  
  public ReturnTO recuperarPorCpf(String cpf) {
    return new ObjectAndMessageReturnTO<Usuario>(dao.buscaPorCpf(cpf));
  }
  
}
