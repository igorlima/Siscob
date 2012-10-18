'use strict';


// Declare app level module which depends on filters, and services
angular.module('siscob', ['usuario']).
  config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/usuario', {templateUrl: 'partials/usuario.html', controller: 'UsuarioCtrl'});
    $routeProvider.when('/publicacao', {templateUrl: 'partials/publicacao.html', controller: 'PublicacaoCtrl'});
    $routeProvider.otherwise({redirectTo: '/siscob'});
  }]);
