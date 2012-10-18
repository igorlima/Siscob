package br.ufla.dcc.siscob.model.service.bo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

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

	public ReturnTO salvar(Publicacao publicacao) {
	  dao.createOrUpdate(publicacao);
	  return new MessageReturnTO();
	}
	
	public ReturnTO editar(Publicacao publicacao) {
    dao.createOrUpdate(publicacao);
    return new MessageReturnTO();
  }
	
	public ReturnTO excluir(Publicacao publicacao) {
	  dao.delete(publicacao);
    return new MessageReturnTO();
  }
	
	public ReturnTO recuperar() {
    return new ObjectAndMessageReturnTO<List<Publicacao>>(dao.retrieve());
  }
	
	public ReturnTO recuperar(Long id) {
	  return new ObjectAndMessageReturnTO<Publicacao>(dao.retrieve(id));
	}
	
	public ReturnTO recuperarPorAutores(String autores) {
	  return new ObjectAndMessageReturnTO<List<Publicacao>>(dao.buscarPorAutores(autores));
	}
	
	public ReturnTO recuperarPorTitulo(@PathVariable String titulo) {
	  return new ObjectAndMessageReturnTO<List<Publicacao>>(dao.buscarPorTitulo(titulo));
	}
	
}
