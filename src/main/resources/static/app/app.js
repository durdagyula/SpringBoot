//(function () {
//    'use strict';
//    angular.module('app', []);
//})();


(function () {
    'use strict';
    angular.module('app', ['ngRoute'])
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
