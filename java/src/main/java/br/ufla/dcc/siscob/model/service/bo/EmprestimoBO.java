package br.ufla.dcc.siscob.model.service.bo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufla.dcc.siscob.model.domain.entity.Emprestimo;
import br.ufla.dcc.siscob.model.domain.entity.ItemEmprestimo;
import br.ufla.dcc.siscob.model.domain.entity.Publicacao;
import br.ufla.dcc.siscob.model.persistence.dao.EmprestimoDAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAOImplementation;
import br.ufla.lemaf.commons.model.service.to.MessageReturnTO;
import br.ufla.lemaf.commons.model.service.to.ObjectAndMessageReturnTO;
import br.ufla.lemaf.commons.model.service.to.ReturnTO;
import br.ufla.lemaf.commons.model.service.to.ReturnTO.Status;

@Named
public class EmprestimoBO {

  @DAO( implementation = DAOImplementation.HIBERNATE )
  @Inject private EmprestimoDAO dao;
  @Inject private PublicacaoBO publicacaoBO;

  @Transactional
  public ReturnTO salvar(Emprestimo emprestimo) {
    if (!possuiDisponibilidade(emprestimo.getItens())) 
      return new MessageReturnTO(Status.ERROR, "A quantidade de exemplares solicitada é superior à quantidade disponível.");
    
    for (ItemEmprestimo item : emprestimo.getItens()) item.setAtivo(true).setEmprestimo(emprestimo).setDataEmprestimo(new Date());
    dao.createOrUpdate(emprestimo.setAtivo(true));
    return new MessageReturnTO();
  }
  
  @Transactional
  public ReturnTO editar(Emprestimo emprestimo) {
    if (!possuiDisponibilidade(emprestimo.getItens())) 
      return new MessageReturnTO(Status.ERROR, "A quantidade de exemplares solicitada é superior à quantidade disponível.");
    
    for (ItemEmprestimo item : emprestimo.getItens())
      if (item.getEmprestimo()!=null) item.setAtivo(true).setEmprestimo(emprestimo);
      else item.setAtivo(true).setEmprestimo(emprestimo).setDataEmprestimo(new Date());
    dao.createOrUpdate(emprestimo.setAtivo(true));
    return new MessageReturnTO();
  }
  
  @Transactional
  public ReturnTO excluir(Long id) {
    dao.retrieve(id).setAtivo(false);
    return new MessageReturnTO();
  }
  
  @Transactional(readOnly=true)
  public ReturnTO recuperar() {
    return new ObjectAndMessageReturnTO<List<Emprestimo>>(dao.retrieve());
  }
  
  @Transactional(readOnly=true)
  public ReturnTO recuperar(Long id) {
    return new ObjectAndMessageReturnTO<Emprestimo>(dao.retrieve(id));
  }
  
  
  private boolean possuiDisponibilidade(List<ItemEmprestimo> itens) {
    Map<Long, Long> qteSolicitadas = new HashMap<Long, Long>();
    for (ItemEmprestimo item : itens) {
      Long idPublicacao = item.getPublicacao().getId();
      if (!qteSolicitadas.containsKey(idPublicacao)) qteSolicitadas.put(idPublicacao, 1L );
      else qteSolicitadas.put(idPublicacao, qteSolicitadas.get(idPublicacao)+1L );
    }
    
    for (Long id : qteSolicitadas.keySet()) {
      Long qteDisponivel = publicacaoBO.qteDisponivel(new Publicacao().setId(id));
      if (qteSolicitadas.get(id) > qteDisponivel) return false;
    }
    
    return true;
  }
}
