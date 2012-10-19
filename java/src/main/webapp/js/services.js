'use strict';

/* Services */
angular.module('usuarioModel', ['ngResource']).
factory('Usuario', ['$resource', function($resource){
  return $resource('usuario/:id', {}, {
    all:    {method:'GET' , params:{}},
    save:   {method:'POST', params:{}},
    update: {method:'PUT', params:{}},
    get:    {method:'GET' , params:{id:'@id'}},
    remove: {method:'DELETE' , params:{id:'@id'}}
  });
}]);


angular.module('publicacaoModel', ['ngResource']).
factory('Publicacao', ['$resource', function($resource){
  return $resource('publicacao/:id', {}, {
    all:    {method:'GET' , params:{}},
    save:   {method:'POST', params:{}},
    update: {method:'PUT', params:{}},
    get:    {method:'GET' , params:{id:'@id'}},
    remove: {method:'DELETE' , params:{id:'@id'}}
  });
}]);
