package br.ufla.dcc.siscob.model.service.bo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import br.ufla.dcc.siscob.model.domain.entity.Publicacao;
import br.ufla.dcc.siscob.model.persistence.dao.PublicacaoDAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAOImplementation;
import br.ufla.lemaf.commons.model.service.to.MessageReturnTO;
import br.ufla.lemaf.commons.model.service.to.ObjectAndMessageReturnTO;
import br.ufla.lemaf.commons.model.service.to.ReturnTO;

@Named
public class PublicacaoBO {

  @DAO( implementation = DAOImplementation.HIBERNATE )
  @Inject private PublicacaoDAO dao;

  @Transactional
  public ReturnTO salvar(Publicacao publicacao) {
    dao.createOrUpdate(publicacao);
    return new MessageReturnTO();
  }
  
  @Transactional
  public ReturnTO editar(Publicacao publicacao) {
    dao.createOrUpdate(publicacao);
    return new MessageReturnTO();
  }
  
  @Transactional
  public ReturnTO excluir(Long id) {
    dao.delete( dao.retrieve(id) );
    return new MessageReturnTO();
  }
  
  @Transactional(readOnly=true)
  public ReturnTO recuperar() {
    return new ObjectAndMessageReturnTO<List<Publicacao>>(dao.retrieve());
  }
  
  @Transactional(readOnly=true)
  public ReturnTO recuperar(Long id) {
    return new ObjectAndMessageReturnTO<Publicacao>(dao.retrieve(id));
  }
  
  @Transactional(readOnly=true)
  public ReturnTO recuperarPorAutores(String autores) {
    return new ObjectAndMessageReturnTO<List<Publicacao>>(dao.buscarPorAutores(autores));
  }
  
  @Transactional(readOnly=true)
  public ReturnTO recuperarPorTitulo(@PathVariable String titulo) {
    return new ObjectAndMessageReturnTO<List<Publicacao>>(dao.buscarPorTitulo(titulo));
  }
  
}
