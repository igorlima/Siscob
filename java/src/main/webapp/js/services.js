'use strict';

/* Services */
angular.module('usuarioModel', ['ngResource']).
  factory('Usuario', ['$resource', function($resource){
    return $resource('usuario/:id', {}, {
      all:    {method:'GET'   , params:{}, isArray:true},
      save:   {method:'POST' },
      update: {method:'PUT'   , params:{id:'@id'}},
      get:    {method:'GET'   , params:{id:'@id'}},
      remove: {method:'DELETE', params:{id:'@id'}}
    });
  }]);
