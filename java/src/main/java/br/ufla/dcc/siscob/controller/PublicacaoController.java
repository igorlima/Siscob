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
public class PublicacaoController {
	
  @Inject PublicacaoBO bo;
	
  @RequestMapping(value = "/publicacao", method = RequestMethod.PUT)
	public ReturnTO salvar(@RequestBody Publicacao publicacao) {
    return bo.salvar(publicacao);
	}
  
  @RequestMapping(value = "/publicacao", method = RequestMethod.POST)
  public ReturnTO editar(@RequestBody Publicacao publicacao) {
    return bo.editar(publicacao);
  }
	
  @RequestMapping(value = "/publicacao", method = RequestMethod.DELETE)
	public ReturnTO remove(@RequestBody Publicacao publicacao) {
		return bo.excluir(publicacao);
	}
  
  @RequestMapping(value = "/publicacao", method = RequestMethod.GET)
  public ReturnTO recuperar(){
    return bo.recuperar();
  }
	
  @RequestMapping(value = "/publicacao/{id}", method = RequestMethod.GET)
	public ReturnTO recuperar(@PathVariable Long id){
		return bo.recuperar(id);
	}
	
  @RequestMapping(value = "/publicacao/autores/{autores}", method = RequestMethod.GET)
	public ReturnTO recuperarPorAutores(@PathVariable String autores){
    return bo.recuperarPorAutores(autores);
	}
	
  @RequestMapping(value = "/publicacao/titulo/{titulo}", method = RequestMethod.GET)
	public ReturnTO recuperarPorTitulo(@PathVariable String titulo){
    return bo.recuperarPorTitulo(titulo);
	}
  
}
