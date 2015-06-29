angular.module('christmasapp').service('registrationservice', function($resource) {
    return $resource('/register/admin');
});
