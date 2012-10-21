package br.ufla.dcc.siscob.model.service.bo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufla.dcc.siscob.model.domain.entity.ItemEmprestimo;
import br.ufla.dcc.siscob.model.domain.entity.Publicacao;
import br.ufla.dcc.siscob.model.persistence.dao.ItemEmprestimoDAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAOImplementation;
import br.ufla.lemaf.commons.model.service.to.MessageReturnTO;
import br.ufla.lemaf.commons.model.service.to.ObjectAndMessageReturnTO;
import br.ufla.lemaf.commons.model.service.to.ReturnTO;

@Named
public class ItemEmprestimoBO {

  @DAO( implementation = DAOImplementation.HIBERNATE )
  @Inject private ItemEmprestimoDAO dao;
  @Inject private PublicacaoBO publicacaoBO; 

  @Transactional
  public ReturnTO salvar(ItemEmprestimo itemEmprestimo) {
    dao.createOrUpdate(itemEmprestimo.setAtivo(true));
    return new MessageReturnTO();
  }
  
  @SuppressWarnings("unchecked")
  @Transactional
  public ReturnTO editar(ItemEmprestimo itemEmprestimo) {
    ReturnTO publicacaoTO = publicacaoBO.recuperar(itemEmprestimo.getPublicacao().getId());
    
    dao.retrieve(itemEmprestimo.getId())
    .setPublicacao( ((ObjectAndMessageReturnTO<Publicacao>) publicacaoTO).getReturnObject() );
    
    return new MessageReturnTO();
  }
  
  @Transactional
  public ReturnTO excluir(Long id) {
    dao.retrieve(id).setAtivo(false);
    return new MessageReturnTO();
  }
  
  @Transactional(readOnly=true)
  public ReturnTO recuperar() {
    return new ObjectAndMessageReturnTO<List<ItemEmprestimo>>(dao.retrieve());
  }
  
  @Transactional(readOnly=true)
  public ReturnTO recuperar(Long id) {
    return new ObjectAndMessageReturnTO<ItemEmprestimo>(dao.retrieve(id));
  }
  
}
