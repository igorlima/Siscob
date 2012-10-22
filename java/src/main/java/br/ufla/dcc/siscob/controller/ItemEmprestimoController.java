package br.ufla.dcc.siscob.controller;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufla.dcc.siscob.model.domain.entity.Emprestimo;
import br.ufla.dcc.siscob.model.service.bo.ItemEmprestimoBO;
import br.ufla.lemaf.commons.model.service.to.ReturnTO;


@Named
@RequestMapping( value = "itememprestimo/**" )
public class ItemEmprestimoController extends ApplicationController {
  
  @Inject ItemEmprestimoBO bo;
  
  @RequestMapping(value = "/itememprestimo/devolucao", method = RequestMethod.PUT)
  public ReturnTO devolver(@RequestBody Emprestimo emprestimo) {
    return bo.devolver(emprestimo.getItens());
  }
  
  @RequestMapping(value = "/itememprestimo/renovacao", method = RequestMethod.PUT)
  public ReturnTO renovar(@RequestBody Emprestimo emprestimo) {
    return bo.renovar(emprestimo.getItens());
  }
  
}
