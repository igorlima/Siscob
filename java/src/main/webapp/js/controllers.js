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
  
  ng.publicacao = {tipo:'LIVRO'};
  
  ng.publicacoes = []; 
  
  var listar_todas_publicacoes = function() {
    Publicacao.all(function(data){
      ng.publicacoes = data.returnObject;
      ng.has_publicacoes = ng.publicacoes.length > 0 ? true : false;
    });
  };
  listar_todas_publicacoes();
  
  ng.salvar = function() {
    Publicacao.save( {tipo:ng.publicacao.tipo}, ng.publicacao, 
      function(data){
        if (data.status=='ERROR') Message.set(true, data.message);
        else{
          Message.set(false, data.message);
          ng.publicacao = {tipo:'LIVRO'};
          listar_todas_publicacoes();
        }
      },
      function(data){
        Message.set(true, data);
    });
  };
  
  ng.editar_publicacao_selecionado = function() {
    Publicacao.update( {tipo:ng.publicacao.tipo}, ng.publicacao, 
      function(data){
        if (data.status=='ERROR') Message.set(true, data.message);
        else{
          Message.set(false, data.message);
          ng.publicacao = {tipo:'LIVRO'};
          listar_todas_publicacoes();
        }
      },
      function(data){
        Message.set(true, data);
    });
  };
  
  ng.editar = function(publicacao) {
    ng.publicacao.id            = publicacao.id;
    ng.publicacao.titulo        = publicacao.titulo;
    ng.publicacao.editora       = publicacao.editora;
    ng.publicacao.ano           = publicacao.ano;
    ng.publicacao.tipo          = publicacao.tipo;
    ng.publicacao.autores       = publicacao.autores;
    ng.publicacao.qtdExemplares = publicacao.qtdExemplares;
    ng.publicacao.numEdicao     = publicacao.numEdicao;
    ng.publicacao.mes           = publicacao.mes;
  };
  
  ng.visualizar = function(publicacao) {
    ng.publicacao_para_visualizacao = publicacao;
    ng.publicacao = {tipo:'LIVRO'};
  };
  
  ng.excluir = function(publicacao) {
    Publicacao.remove( {id:publicacao.id, tipo:publicacao.tipo},
      function(data) {
        if (data.status=='ERROR') Message.set(true, data.message);
        else{
          Message.set(false, data.message);
          ng.publicacao = {tipo:'LIVRO'};
          listar_todas_publicacoes();
        }
      },
      function(data) {
        Message.set(true, data);
        ng.publicacao = {tipo:'LIVRO'};
    });
  };

}])


.controller( 'ItemEmprestimoCtrl', ['$scope','ItemEmprestimo','Usuario','Publicacao', 
function(ng, ItemEmprestimo, Usuario, Publicacao) {
  
  var create_a_new_item_emprestimo = function() {
    return {
      usuario: {},
      publicacao: {}
    };
  };
  
  ng.item_emprestimo = create_a_new_item_emprestimo();
  
  ng.item_emprestimos = [];
  ng.usuarios = [];
  ng.livros = [];
  
  Publicacao.all( {tipo:'livros'}, function(data){
    ng.livros = data.returnObject;
  });
  
  Usuario.all(function(data){
    ng.usuarios = data.returnObject;
  });
  
  var listar_todos_item_emprestimos = function() {
    ItemEmprestimo.all(function(data){
      ng.item_emprestimos = data.returnObject;
      ng.has_item_emprestimos = ng.item_emprestimos.length > 0 ? true : false;
    });
  };
  listar_todos_item_emprestimos();
  
  ng.salvar = function() {
    ItemEmprestimo.save( {tipo:ng.item_emprestimo.tipo}, ng.item_emprestimo, 
      function(data){
        if (data.status=='ERROR') Message.set(true, data.message);
        else{
          Message.set(false, data.message);
          ng.item_emprestimo = create_a_new_item_emprestimo();
          listar_todos_item_emprestimos();
        }
      },
      function(data){
        Message.set(true, data);
    });
  };
  
  ng.editar_item_emprestimo_selecionado = function() {
    ItemEmprestimo.update( {tipo:ng.item_emprestimo.tipo}, ng.item_emprestimo, 
      function(data){
        if (data.status=='ERROR') Message.set(true, data.message);
        else{
          Message.set(false, data.message);
          ng.item_emprestimo = create_a_new_item_emprestimo();
          listar_todos_item_emprestimos();
        }
      },
      function(data){
        Message.set(true, data);
    });
  };
  
  ng.editar = function(item_emprestimo) {
    ng.item_emprestimo.id            = item_emprestimo.id;
    ng.item_emprestimo.publicacao.id = item_emprestimo.publicacao.id;
    ng.item_emprestimo.usuario.id    = item_emprestimo.usuario.id;
  };
  
  ng.visualizar = function(item_emprestimo) {
    ng.item_emprestimo_para_visualizacao = item_emprestimo;
    ng.item_emprestimo = create_a_new_item_emprestimo();
  };
  
  ng.excluir = function(item_emprestimo) {
    ItemEmprestimo.remove( {id:item_emprestimo.id},
      function(data) {
        if (data.status=='ERROR') Message.set(true, data.message);
        else{
          Message.set(false, data.message);
          ng.item_emprestimo = create_a_new_item_emprestimo();
          listar_todos_item_emprestimos();
        }
      },
      function(data) {
        Message.set(true, data);
        ng.item_emprestimo = create_a_new_item_emprestimo();
    });
  };

}]);
