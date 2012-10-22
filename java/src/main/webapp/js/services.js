'use strict';

/* Services */
angular.module('usuarioModel', ['ngResource']).
factory('Usuario', ['$resource', function($resource){
  return $resource('usuario/:id', {}, {
    all:    {method:'GET'   , params:{}},
    save:   {method:'POST'  , params:{}},
    update: {method:'PUT'   , params:{}},
    get:    {method:'GET'   , params:{id:'@id'}},
    remove: {method:'DELETE', params:{id:'@id'}}
  });
}]);


angular.module('publicacaoModel', ['ngResource']).
factory('Publicacao', ['$resource', function($resource){
  return $resource('publicacao/:id/:pathTipo/:tipo', {}, {
    all:    {method:'GET'   , params:{}},
    save:   {method:'POST'  , params:{}},
    update: {method:'PUT'   , params:{}},
    get:    {method:'GET'   , params:{id:'@id'}},
    remove: {method:'DELETE', params:{id:'@id'}}
  });
}]);

angular.module('emprestimoModel', ['ngResource']).
factory('Emprestimo', ['$resource', function($resource) {
  return $resource('emprestimo/:id', {}, {
    all:     {method:'GET'   , params:{}},
    save:    {method:'POST'  , params:{}},
    update:  {method:'PUT'   , params:{}},
    get:     {method:'GET'   , params:{id:'@id'}},
    remove:  {method:'DELETE', params:{id:'@id'}}
  });
}]);

angular.module('itemEmprestimoModel', ['ngResource']).
factory('ItemEmprestimo', ['$resource', function($resource) {
  return $resource('itememprestimo/:act', {}, {
    devolve: {method:'PUT', params:{act:'devolucao'}},
    renew:   {method:'PUT', params:{act:'renovacao'}}
  });
}]);

