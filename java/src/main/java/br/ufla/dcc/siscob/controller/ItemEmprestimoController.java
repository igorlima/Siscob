package br.ufla.dcc.siscob.controller;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufla.dcc.siscob.model.domain.entity.ItemEmprestimo;
import br.ufla.dcc.siscob.model.service.bo.ItemEmprestimoBO;
import br.ufla.lemaf.commons.model.service.to.ReturnTO;


@Named
@RequestMapping( value = "itememprestimo/**" )
public class ItemEmprestimoController extends ApplicationController {
  
  @Inject ItemEmprestimoBO bo;
  
  @RequestMapping(value = "/itememprestimo", method = RequestMethod.POST)
  public ReturnTO salvar(@RequestBody ItemEmprestimo itememprestimo) {
    return bo.salvar(itememprestimo);
  }
  
  @RequestMapping(value = "/itememprestimo", method = RequestMethod.PUT)
  public ReturnTO editar(@RequestBody ItemEmprestimo itememprestimo) {
    return bo.editar(itememprestimo);
  }
  
  @RequestMapping(value = "/itememprestimo/{id}", method = RequestMethod.DELETE)
  public ReturnTO remove(@PathVariable Long id) {
    return bo.excluir(id);
  }
  
  @RequestMapping(value = "/itememprestimo", method = RequestMethod.GET)
  public ReturnTO recuperar(){
    return bo.recuperar();
  }
  
  @RequestMapping(value = "/itememprestimo/{id}", method = RequestMethod.GET)
  public ReturnTO recuperar(@PathVariable Long id) {
    return bo.recuperar(id);
  }
  
}
