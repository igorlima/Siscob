'use strict';

/* Controllers */

angular.module( 'usuario', ['usuarioModel'] )

.controller( 'UsuarioCtrl', ['$scope', 'Usuario', 
  function(ng, Usuario) {
  
    ng.usuario = {};
    
    ng.usuarios = []; 
    
    var listar_todos_usuarios = function() {
      Usuario.all(function(){
        ng.has_usuarios = ng.usuarios.length > 0 ? true : false;
      });
    };
    listar_todos_usuarios();
    
    ng.salvar = function() {
      Usuario.save( {}, ng.usuario, 
        function(data){
          Message.set(false, data.message);
          ng.usuario = {};
          listar_todos_usuarios();
        },
        function(data){
          Message.set(true, data.message);
      });
    };
    
    ng.editar_usuario_selecionado = function() {
      Usuario.edit( {id:ng.usuario.id}, ng.usuario, 
        function(data){
          Message.set(false, data.message);
          ng.usuario = {};
          listar_todos_usuarios();
        },
        function(data){
          Message.set(true, data.message);
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
      ng.usuario = {};
    };
    
    ng.excluir = function(usuario) {
      Usuario.remove( {id:ng.usuario.id},
        function(data) {
          Message.set(false, data.message);
          ng.usuario = {};
          listar_todos_usuarios();
        },
        function(data) {
          Message.set(true, data.message);
          ng.usuario = {};
      });
    };
  
  }])

.controller( 'PublicacaoCtrl', ['$scope', 
  function(ng) {
    
  }]);
