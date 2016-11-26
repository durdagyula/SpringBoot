(function () {
    'use strict';

    angular.module('app').controller('UserController', UserController);

    UserController.$inject = ['$http'];

    function UserController($http){
        var vm = this;
        
        vm.users = [];
        vm.getAll = getAll;
        vm.getAdmins = getAdmins;
        vm.deleteUser = deleteUser;
        
        init();
        
        function init() {
            getAll();
        }

        function getAll() {
            var url = "/users/all";
            var userPromise = $http.get(url);
            userPromise.then(function (response) {
                vm.users = response.data;
            });
        }

        function getAdmins() {
            var url = "/users/admin/";
            var userPromise = $http.get(url);
            userPromise.then(function (response) {
                vm.users = response.data;
            });
        }

        function deleteUser(id) {
            var url = "/users/delete/" + id;
            $http.post(url).then(function (response) {
                vm.users = response.data;
            });
        }
    }
})();
