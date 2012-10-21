package br.ufla.dcc.siscob.model.service.bo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import br.ufla.dcc.siscob.model.domain.entity.LivroAttr;
import br.ufla.dcc.siscob.model.domain.entity.PeriodicoAttr;
import br.ufla.dcc.siscob.model.domain.entity.Publicacao;
import br.ufla.dcc.siscob.model.persistence.dao.LivroAttrDAO;
import br.ufla.dcc.siscob.model.persistence.dao.PeriodicoAttrDAO;
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
  
  @DAO( implementation = DAOImplementation.HIBERNATE )
  @Inject private LivroAttrDAO livroAttrDAO;
  
  @DAO( implementation = DAOImplementation.HIBERNATE )
  @Inject private PeriodicoAttrDAO PeriodicoAttrDAO;

  @Transactional
  public ReturnTO salvar(Publicacao publicacao) {
    dao.createOrUpdate(publicacao.setAtivo(true));
    return new MessageReturnTO();
  }
  
  @Transactional
  public ReturnTO editar(Publicacao publicacao) {
    dao.retrieve(publicacao.getId())
    .setAno(publicacao.getAno())
    .setEditora(publicacao.getEditora())
    .setTipo(publicacao.getTipo()==null?Publicacao.Publicacao:publicacao.getTipo())
    .setTitulo(publicacao.getTitulo());
    
    if (Publicacao.Livro.equals(publicacao.getTipo())) 
      livroAttrDAO.createOrUpdate( new LivroAttr(publicacao) );
    else if (Publicacao.Periodico.equals(publicacao.getTipo()))
      PeriodicoAttrDAO.createOrUpdate( new PeriodicoAttr(publicacao) );
    
    return new MessageReturnTO();
  }
  
  @Transactional
  public ReturnTO excluir(Long id) {
    dao.retrieve(id).setAtivo(false);
    return new MessageReturnTO();
  }
  
  @Transactional(readOnly=true)
  public ReturnTO recuperar() {
    return new ObjectAndMessageReturnTO<List<Publicacao>>(dao.retrieve());
  }
  
  @Transactional(readOnly=true)
  public ReturnTO recuperar(String tipo) {
    return new ObjectAndMessageReturnTO<List<Publicacao>>(dao.retrieve(tipo));
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
