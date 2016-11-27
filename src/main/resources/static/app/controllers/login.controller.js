(function () {
    'use strict';

    angular.module('app').controller('LoginController',function ($scope) {
        $scope.login = function (user) {
            console.log('enter function');
        }
    });

})();