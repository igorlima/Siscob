package br.ufla.dcc.siscob.model.service.bo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

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

  @Transactional
  public ReturnTO salvar(Usuario usuario) {
    dao.createOrUpdate(usuario);
    return new MessageReturnTO();
  }
  
  @Transactional
  public ReturnTO editar(Usuario usuario) {
    dao.createOrUpdate(usuario);
    return new MessageReturnTO();
  }
  
  @Transactional
  public ReturnTO excluir(Long id) {
    dao.delete(dao.retrieve(id));
    return new MessageReturnTO();
  }
  
  @Transactional(readOnly=true)
  public ReturnTO recuperar() {
    return new ObjectAndMessageReturnTO<List<Usuario>>(dao.retrieve());
  }
  
  @Transactional(readOnly=true)
  public ReturnTO recuperar(Long id) {
    return new ObjectAndMessageReturnTO<Usuario>(dao.retrieve(id));
  }
  
  @Transactional(readOnly=true)
  public ReturnTO recuperarPorNome(String nome) {
    return new ObjectAndMessageReturnTO<List<Usuario>>(dao.listaUsuarioPorNome(nome));
  }
  
  @Transactional(readOnly=true)
  public ReturnTO recuperarPorCpf(String cpf) {
    return new ObjectAndMessageReturnTO<Usuario>(dao.buscaPorCpf(cpf));
  }
  
}
