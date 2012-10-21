package br.ufla.dcc.siscob.controller;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

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
  
  @RequestMapping(value = "/itememprestimo/devolucao", method = RequestMethod.PUT)
  public ReturnTO devolver(@RequestBody List<ItemEmprestimo> itens) {
    return bo.devolver(itens);
  }
  
  @RequestMapping(value = "/itememprestimo/renovacao", method = RequestMethod.PUT)
  public ReturnTO renovar(@RequestBody List<ItemEmprestimo> itens) {
    return bo.renovar(itens);
  }
  
}
