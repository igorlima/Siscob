package br.ufla.dcc.siscob.model.service.bo;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufla.dcc.siscob.model.domain.entity.Emprestimo;
import br.ufla.dcc.siscob.model.domain.entity.ItemEmprestimo;
import br.ufla.dcc.siscob.model.persistence.dao.EmprestimoDAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAOImplementation;
import br.ufla.lemaf.commons.model.service.to.MessageReturnTO;
import br.ufla.lemaf.commons.model.service.to.ObjectAndMessageReturnTO;
import br.ufla.lemaf.commons.model.service.to.ReturnTO;

@Named
public class EmprestimoBO {

  @DAO( implementation = DAOImplementation.HIBERNATE )
  @Inject private EmprestimoDAO dao;

  @Transactional
  public ReturnTO salvar(Emprestimo emprestimo) {
    for (ItemEmprestimo item : emprestimo.getItens()) item.setAtivo(true).setEmprestimo(emprestimo);
    dao.createOrUpdate(emprestimo.setAtivo(true).setDataEmprestimo(new Date()));
    return new MessageReturnTO();
  }
  
  @Transactional
  public ReturnTO editar(Emprestimo emprestimo) {
    dao.createOrUpdate(emprestimo);
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
  
}
