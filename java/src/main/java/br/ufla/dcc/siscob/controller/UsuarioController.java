package br.ufla.dcc.siscob.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufla.dcc.siscob.model.domain.entity.Usuario;
import br.ufla.dcc.siscob.model.service.bo.UsuarioBO;
import br.ufla.lemaf.commons.model.service.to.ReturnTO;


@Controller
@RequestMapping( value = "usuario" )
public class UsuarioController {
	
	@Inject UsuarioBO bo;
	
	@RequestMapping(value = "/usuario", method = RequestMethod.PUT)
  public ReturnTO salvar(@RequestBody Usuario usuario) {
    return bo.salvar(usuario);
  }
  
  @RequestMapping(value = "/usuario", method = RequestMethod.POST)
  public ReturnTO editar(@RequestBody Usuario usuario) {
    return bo.editar(usuario);
  }
  
  @RequestMapping(value = "/usuario", method = RequestMethod.DELETE)
  public ReturnTO remove(@RequestBody Usuario usuario) {
    return bo.excluir(usuario);
  }
  
  @RequestMapping(value = "/usuario", method = RequestMethod.GET)
  public ReturnTO recuperar(){
    return bo.recuperar();
  }
  
  @RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
  public ReturnTO recuperar(@PathVariable Long id){
    return bo.recuperar(id);
  }
	
  @RequestMapping(value = "/usuario/nome/{nome}", method = RequestMethod.GET)
	public ReturnTO buscaPorNome(@PathVariable String nome){
    return bo.recuperarPorNome(nome);
	}
  
  @RequestMapping(value = "/usuario/cpf/{cpf}", method = RequestMethod.GET)
  public ReturnTO buscaPorCpf(@PathVariable String cpf){
    return bo.recuperarPorCpf(cpf);
  }
	
}