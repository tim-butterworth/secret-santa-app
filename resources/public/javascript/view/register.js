angular.module('christmasapp', ['ngRoute'])
    .config(function($routeProvider) {
	$routeProvider
            .when('/kewl', {
		templateUrl : '/file/javascript/templates/registration.html',
		controller  : 'registration'
            })
    })
    .controller('registration', function($scope) {
	$scope.admin = {};
    });
