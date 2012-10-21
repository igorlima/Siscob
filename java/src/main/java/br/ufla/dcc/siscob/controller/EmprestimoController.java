package br.ufla.dcc.siscob.controller;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufla.dcc.siscob.model.domain.entity.Emprestimo;
import br.ufla.dcc.siscob.model.service.bo.EmprestimoBO;
import br.ufla.lemaf.commons.model.service.to.ReturnTO;


@Named
@RequestMapping( value = "emprestimo/**" )
public class EmprestimoController extends ApplicationController {
  
  @Inject EmprestimoBO bo;
  
  @RequestMapping(value = "/emprestimo", method = RequestMethod.POST)
  public ReturnTO salvar(@RequestBody Emprestimo emprestimo) {
    return bo.salvar(emprestimo);
  }
  
  @RequestMapping(value = "/emprestimo", method = RequestMethod.PUT)
  public ReturnTO editar(@RequestBody Emprestimo emprestimo) {
    return bo.editar(emprestimo.setAtivo(true));
  }
  
  @RequestMapping(value = "/emprestimo/{id}", method = RequestMethod.DELETE)
  public ReturnTO remove(@PathVariable Long id) {
    return bo.excluir(id);
  }
  
  @RequestMapping(value = "/emprestimo", method = RequestMethod.GET)
  public ReturnTO recuperar(){
    return bo.recuperar();
  }
  
  @RequestMapping(value = "/emprestimo/{id}", method = RequestMethod.GET)
  public ReturnTO recuperar(@PathVariable Long id) {
    return bo.recuperar(id);
  }
  
}
