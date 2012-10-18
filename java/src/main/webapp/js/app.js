'use strict';


// Declare app level module which depends on filters, and services
angular.module('siscob', ['siscob.filters', 'siscob.services', 'siscob.directives']).
  config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/usuario', {templateUrl: 'partials/usuario.html', controller: MyCtrl1});
    $routeProvider.when('/publicacao', {templateUrl: 'partials/publicacao.html', controller: MyCtrl2});
    $routeProvider.otherwise({redirectTo: '/siscob'});
  }]);
