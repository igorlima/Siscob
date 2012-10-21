'use strict';

/* Controllers */

angular.module( 'controllers', ['usuarioModel', 'publicacaoModel', 'emprestimoModel'] )

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
  
  ng.limpar = function() {
    ng.usuario = {};
  };
  
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
  
  ng.limpar = function() {
    ng.publicacao = {tipo:'LIVRO'};
  };
  
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


.controller( 'EmprestimoCtrl', ['$scope','Emprestimo','Usuario','Publicacao', 
function(ng, Emprestimo, Usuario, Publicacao) {
  
  var create_a_new_emprestimo = function() {
    ng.publicacao_selecionada = {};
    ng.dataDevolucao = null;
    return {
      usuario: {},
      itens: []
    };
  };
  
  ng.emprestimo = create_a_new_emprestimo();
  ng.emprestimos = [];
  ng.usuarios = [];
  ng.livros = [];
  
  Publicacao.all( {pathTipo: 'tipo', tipo:'LIVRO'}, function(data){
    ng.livros = data.returnObject;
  });
  
  Usuario.all(function(data){
    ng.usuarios = data.returnObject;
  });
  
  var listar_todos_emprestimos = function() {
    Emprestimo.all(function(data){
      ng.emprestimos = data.returnObject;
      ng.has_emprestimos = ng.emprestimos.length > 0 ? true : false;
    });
  };
  listar_todos_emprestimos();
  
  ng.limpar = function() {
    ng.emprestimo = create_a_new_emprestimo();
  };
  
  ng.adicionarItemEmprestimo = function() {
    ng.emprestimo.itens.push({
      dataDevolucao: ng.dataDevolucao,
      publicacao: ng.publicacao_selecionada
    });
    ng.publicacao_selecionada = {};
    ng.dataDevolucao = null;
  };
  
  ng.salvar = function() {
    Emprestimo.save( {tipo:ng.emprestimo.tipo}, ng.emprestimo, 
      function(data){
        if (data.status=='ERROR') Message.set(true, data.message);
        else{
          Message.set(false, data.message);
          ng.emprestimo = create_a_new_emprestimo();
          listar_todos_emprestimos();
        }
      },
      function(data){
        Message.set(true, data);
    });
  };
  
  ng.editar_emprestimo_selecionado = function() {
    Emprestimo.update( {tipo:ng.emprestimo.tipo}, ng.emprestimo, 
      function(data){
        if (data.status=='ERROR') Message.set(true, data.message);
        else{
          Message.set(false, data.message);
          ng.emprestimo = create_a_new_emprestimo();
          listar_todos_emprestimos();
        }
      },
      function(data){
        Message.set(true, data);
    });
  };
  
  ng.editar = function(emprestimo) {
    ng.emprestimo.id             = emprestimo.id;
    ng.emprestimo.itens          = emprestimo.itens;
    ng.emprestimo.usuario.id     = emprestimo.usuario.id;
    ng.emprestimo.dataEmprestimo = emprestimo.dataEmprestimo;
    ng.publicacao_selecionada    = {};
  };
  
  ng.selecionar = function(emprestimo) {
    ng.emprestimo_selecionado = emprestimo;
    ng.emprestimo = create_a_new_emprestimo();
  };
  
  ng.excluir = function(emprestimo) {
    Emprestimo.remove( {id:emprestimo.id},
      function(data) {
        if (data.status=='ERROR') Message.set(true, data.message);
        else{
          Message.set(false, data.message);
          ng.emprestimo = create_a_new_emprestimo();
          listar_todos_emprestimos();
        }
      },
      function(data) {
        Message.set(true, data);
        ng.emprestimo = create_a_new_emprestimo();
    });
  };

}]);
