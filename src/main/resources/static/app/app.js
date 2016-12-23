//(function () {
//    'use strict';
//    angular.module('app', []);
//})();


(function () {
    'use strict';
    angular.module('app', ['ngRoute', 'ngMaterial'])
        .config(function ($routeProvider) {
            $routeProvider
                .when('/login', {
                    templateUrl: '././views/login.html'
                })
                .when('/home', {
                    templateUrl: '././views/home.html',
                    controller: 'UserController'
                })
                .otherwise({
                    redirectTo: '/login'
                });
        });
})();
