(function () {
    'use strict';

        angular.module('app').factory('LoginService', function ($http,$location) {
        return{
            login:function(user, scope){
                var url = "/users/authenticate/" + user.name + '/' +   user.psw;
                $http.post(url).then(function (response) {
                    if(response.data){
                        $location.path('/home');
                    } else {
                        scope.msgtext = 'Wrong username or password';
                        scope.msgsuccess = '';
                        scope.msgerror = '';
                    }
                });
            }
        }
    });

})();