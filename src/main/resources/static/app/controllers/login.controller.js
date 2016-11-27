(function () {
    'use strict';

    angular.module('app').controller('LoginController',function ($scope, LoginService) {
        $scope.msgtext = '';
        $scope.login = function (user) {
           LoginService.login(user, $scope);
        }
    });

})();