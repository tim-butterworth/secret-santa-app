var BASE_TEMPLATE_URL = "file/javascript/app/templates/";

angular.module('christmasapp').config(function($routeProvider, $resourceProvider) {
    $routeProvider
        .when('/register', {
	    templateUrl : BASE_TEMPLATE_URL + 'registration.html',
	    controller  : 'registration'
	}).when('/registered', {
	    templateUrl : BASE_TEMPLATE_URL + 'registered.html'
	}).when('/add-people/:groupId', {
	    templateUrl : BASE_TEMPLATE_URL + 'add_people.html',
	    controller : 'addPeople'
	});
});
