angular.module('christmasapp').service('peopleservice', function($resource){
    return $resource('/group/:groupId/people', {groupId:'@groupId'});
});
