package br.ufla.dcc.siscob.model.service.bo;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufla.dcc.siscob.model.domain.entity.ItemEmprestimo;
import br.ufla.dcc.siscob.model.domain.entity.Publicacao;
import br.ufla.dcc.siscob.model.domain.entity.Usuario;
import br.ufla.dcc.siscob.model.persistence.dao.ItemEmprestimoDAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAOImplementation;
import br.ufla.lemaf.commons.model.service.to.MessageReturnTO;
import br.ufla.lemaf.commons.model.service.to.ReturnTO;

@Named
public class ItemEmprestimoBO {

  @DAO( implementation = DAOImplementation.HIBERNATE )
  @Inject private ItemEmprestimoDAO dao;

  @Transactional
  public ReturnTO devolver(List<ItemEmprestimo> itens) {
    
    for (ItemEmprestimo item : itens)
      if (item.getDevolver())
        dao.retrieve(item.getId()).setDataDaDevolucao(new Date());
    
    return new MessageReturnTO();
  }
  
  @Transactional
  public ReturnTO renovar(List<ItemEmprestimo> itens) {
    
    for (ItemEmprestimo item : itens) {
      if (item.getRenovar()) {
        ItemEmprestimo fromDB = dao.retrieve(item.getId()).setDataDaDevolucao(new Date());
        dao.createOrUpdate(
          new ItemEmprestimo()
          .setAtivo(true)
          .setDataEmprestimo(new Date())
          .setDataParaDevolucao(item.getDataParaDevolucao())
          .setEmprestimo(fromDB.getEmprestimo())
          .setPublicacao(fromDB.getPublicacao())
        );
      }
    }
    
    return new MessageReturnTO();
  }
  
  @Transactional(readOnly=true)
  public Boolean hasEmprestimo(Publicacao publicacao) {
    return dao.hasEmprestimo(publicacao);
  }
  
  @Transactional(readOnly=true)
  public Boolean hasEmprestimo(Usuario usuario) {
    return dao.hasEmprestimo(usuario);
  }
  
}
