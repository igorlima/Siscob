package br.ufla.dcc.siscob.controller;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufla.dcc.siscob.model.domain.entity.Publicacao;
import br.ufla.dcc.siscob.model.service.bo.PublicacaoBO;
import br.ufla.lemaf.commons.model.service.to.ReturnTO;

@Named
@RequestMapping(value = "/publicacao/**")
public class PublicacaoController extends ApplicationController {
	
  @Inject PublicacaoBO publicacaoBO;
	
  @RequestMapping(value = "/publicacao", method = RequestMethod.PUT)
	public ReturnTO salvar(@RequestBody Publicacao publicacao) {
    return publicacaoBO.salvar(publicacao);
	}
  
  @RequestMapping(value = "/publicacao", method = RequestMethod.POST)
  public ReturnTO editar(@RequestBody Publicacao publicacao) {
    return publicacaoBO.editar(publicacao);
  }
	
  @RequestMapping(value = "/publicacao", method = RequestMethod.DELETE)
	public ReturnTO remove(@RequestBody Publicacao publicacao) {
		return publicacaoBO.excluir(publicacao);
	}
  
  @RequestMapping(value = "/publicacao", method = RequestMethod.GET)
  public ReturnTO recuperar(){
    return publicacaoBO.recuperar();
  }
	
  @RequestMapping(value = "/publicacao/{id}", method = RequestMethod.GET)
	public ReturnTO recuperar(@PathVariable Long id){
		return publicacaoBO.recuperar(id);
	}
	
  @RequestMapping(value = "/publicacao/autores/{autores}", method = RequestMethod.GET)
	public ReturnTO recuperarPorAutores(@PathVariable String autores){
    return publicacaoBO.recuperarPorAutores(autores);
	}
	
  @RequestMapping(value = "/publicacao/titulo/{titulo}", method = RequestMethod.GET)
	public ReturnTO recuperarPorTitulo(@PathVariable String titulo){
    return publicacaoBO.recuperarPorTitulo(titulo);
	}
  
}
