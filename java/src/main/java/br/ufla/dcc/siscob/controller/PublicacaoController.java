package br.ufla.dcc.siscob.controller;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufla.dcc.siscob.model.domain.entity.Livro;
import br.ufla.dcc.siscob.model.domain.entity.Periodico;
import br.ufla.dcc.siscob.model.domain.entity.Publicacao;
import br.ufla.dcc.siscob.model.service.bo.PublicacaoBO;
import br.ufla.lemaf.commons.model.service.to.ReturnTO;

@Named
@RequestMapping(value = "/publicacao/**")
public class PublicacaoController extends ApplicationController {
  
  @Inject PublicacaoBO publicacaoBO;
  
  @RequestMapping(value = "/publicacao", method = RequestMethod.POST)
  public ReturnTO salvar(@RequestBody Publicacao publicacao) {
    return publicacaoBO.salvar(publicacao);
  }
  
  @RequestMapping(value = "/publicacao/LIVRO", method = RequestMethod.POST)
  public ReturnTO salvarLivro(@RequestBody Livro livro) {
    return publicacaoBO.salvar(livro);
  }
  
  @RequestMapping(value = "/publicacao/PERIODICO", method = RequestMethod.POST)
  public ReturnTO salvarPeriodico(@RequestBody Periodico periodico) {
    return publicacaoBO.salvar(periodico);
  }
  
  @RequestMapping(value = "/publicacao", method = RequestMethod.PUT)
  public ReturnTO editar(@RequestBody Publicacao publicacao) {
    return publicacaoBO.editar(publicacao);
  }
  
  @RequestMapping(value = "/publicacao/LIVRO", method = RequestMethod.PUT)
  public ReturnTO editarLivro(@RequestBody Livro livro) {
    return publicacaoBO.editar(livro);
  }
  
  @RequestMapping(value = "/publicacao/PERIODICO", method = RequestMethod.PUT)
  public ReturnTO editarPeriodico(@RequestBody Periodico periodico) {
    return publicacaoBO.editar(periodico);
  }
  
  @RequestMapping(value = "/publicacao/{id}", method = RequestMethod.DELETE)
  public ReturnTO remove(@PathVariable Long id) {
    return publicacaoBO.excluir(id);
  }
  
  @RequestMapping(value = "/publicacao/{id}/LIVRO", method = RequestMethod.DELETE)
  public ReturnTO removeLivro(@PathVariable Long id) {
    return publicacaoBO.excluir(id);
  }
  
  @RequestMapping(value = "/publicacao/{id}/PERIODICO", method = RequestMethod.DELETE)
  public ReturnTO removePeriodico(@PathVariable Long id) {
    return publicacaoBO.excluir(id);
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
