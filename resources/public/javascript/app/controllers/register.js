angular.module('christmasapp').controller('registration', function($scope, $location, registrationservice) {
    $scope.admin = {};
    $scope.showModel = function(){
	$scope.show = true;
    };
    $scope.navigate = function(){
	var success = function(result){ 
	    $location.path('registered');
	};
	var failure = function(result){
	    $scope.errors = ["Failed to register"];
	};
	$scope.result = registrationservice
	    .save($scope.admin,
		  success,
		  failure);
    };
});
