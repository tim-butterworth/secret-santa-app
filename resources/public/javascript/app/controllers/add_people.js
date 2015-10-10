angular.module('christmasapp').controller('addPeople', function($scope, $location, $routeParams, peopleservice) {
    var adminId = $routeParams.adminId;

    var success = function(result){
    };

    var failure = function(result){
	$scope.errors = ["Failed to save people"];
    };

    $scope.people = peopleservice.query($routeParams);
    $scope.addPerson = function(){
	$scope.people.push({name: 'added'});
    };
    $scope.submit = function(){
	peopleservice
	    .save($scope.people, 
		  success, 
		  failure);
    };
});
