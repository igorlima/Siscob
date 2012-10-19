'use strict';

/* Controllers */

angular.module( 'usuario', ['usuarioModel', 'publicacaoModel'] )

.controller( 'UsuarioCtrl', ['$scope', 'Usuario', 
function(ng, Usuario) {

  ng.usuario = {};
  
  ng.usuarios = []; 
  
  var listar_todos_usuarios = function() {
    Usuario.all(function(data){
      ng.usuarios = data.returnObject;
      ng.has_usuarios = ng.usuarios.length > 0 ? true : false;
    });
  };
  listar_todos_usuarios();
  
  ng.salvar = function() {
    Usuario.save( {}, ng.usuario, 
      function(data){
        if (data.status=='ERROR') Message.set(true, data.message);
        else{
          Message.set(false, data.message);
          ng.usuario = {};
          listar_todos_usuarios();
        }
      },
      function(data){
        Message.set(true, data);
    });
  };
  
  ng.editar_usuario_selecionado = function() {
    Usuario.update( {}, ng.usuario, 
      function(data){
        if (data.status=='ERROR') Message.set(true, data.message);
        else{
          Message.set(false, data.message);
          ng.usuario = {};
          listar_todos_usuarios();
        }
      },
      function(data){
        Message.set(true, data);
    });
  };
  
  ng.editar = function(usuario) {
    ng.usuario.id              = usuario.id;
    ng.usuario.nome            = usuario.nome;
    ng.usuario.endereco        = usuario.endereco;
    ng.usuario.cpf             = usuario.cpf;
    ng.usuario.telefone        = usuario.telefone;
    ng.usuario.dataPenalizacao = usuario.dataPenalizacao;
  };
  
  ng.visualizar = function(usuario) {
    ng.usuario_para_visualizacao = usuario;
    ng.usuario = {};
  };
  
  ng.excluir = function(usuario) {
    Usuario.remove( {id:usuario.id},
      function(data) {
        if (data.status=='ERROR') Message.set(true, data.message);
        else{
          Message.set(false, data.message);
          ng.usuario = {};
          listar_todos_usuarios();
        }
      },
      function(data) {
        Message.set(true, data);
        ng.usuario = {};
    });
  };

}])

.controller( 'PublicacaoCtrl', ['$scope','Publicacao', 
function(ng, Publicacao) {
  
  ng.publicacao = {};
  
  ng.publicacoes = []; 
  
  var listar_todas_publicacoes = function() {
    Publicacao.all(function(data){
      ng.publicacoes = data.returnObject;
      ng.has_publicacoes = ng.publicacoes.length > 0 ? true : false;
    });
  };
  listar_todas_publicacoes();
  
  ng.salvar = function() {
    Publicacao.save( {}, ng.publicacao, 
      function(data){
        if (data.status=='ERROR') Message.set(true, data.message);
        else{
          Message.set(false, data.message);
          ng.publicacao = {};
          listar_todas_publicacoes();
        }
      },
      function(data){
        Message.set(true, data);
    });
  };
  
  ng.editar_publicacao_selecionado = function() {
    Publicacao.update( {}, ng.publicacao, 
      function(data){
        if (data.status=='ERROR') Message.set(true, data.message);
        else{
          Message.set(false, data.message);
          ng.publicacao = {};
          listar_todas_publicacoes();
        }
      },
      function(data){
        Message.set(true, data);
    });
  };
  
  ng.editar = function(publicacao) {
    ng.publicacao.id      = publicacao.id;
    ng.publicacao.titulo  = publicacao.titulo;
    ng.publicacao.editora = publicacao.editora;
    ng.publicacao.ano     = publicacao.ano;
  };
  
  ng.visualizar = function(publicacao) {
    ng.publicacao_para_visualizacao = publicacao;
    ng.publicacao = {};
  };
  
  ng.excluir = function(publicacao) {
    Publicacao.remove( {id:publicacao.id},
      function(data) {
        if (data.status=='ERROR') Message.set(true, data.message);
        else{
          Message.set(false, data.message);
          ng.publicacao = {};
          listar_todas_publicacoes();
        }
      },
      function(data) {
        Message.set(true, data);
        ng.publicacao = {};
    });
  };

}]);
