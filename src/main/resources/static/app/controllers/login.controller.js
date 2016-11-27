(function () {
    'use strict';

    angular.module('app').controller('LoginController',function ($http, $scope, LoginService) {
        $scope.msgsuccess = '';
        $scope.msgerror = '';
        $scope.login = function (user) {
           LoginService.login(user, $scope);
        }

        $scope.register = function (newUser) {
            var url = "/users/register/" + newUser.name + '/' + newUser.credits + '/' + newUser.password;
            var userPromise = $http.post(url)
            $scope.msgsuccess = '';
            $scope.msgerror = '';
            userPromise.then(function (response) {
                if(response.data){
                    $scope.msgsuccess = 'User registered successfully!';
                }else{
                    $scope.msgerror = 'User name is reserved!';
                }
            })
        }
        
        $scope.openModal = function () {
            $scope.msgtext = '';
            $scope.newUser = null;
        }
    });

})();