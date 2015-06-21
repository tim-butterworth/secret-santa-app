angular.module('christmasapp').controller('registration', function($scope, $location) {
    $scope.admin = {};
    $scope.showModel = function(){
	$scope.show = true;
    };
    $scope.navigate = function(){
	$location.path('/registered');
    };
});
