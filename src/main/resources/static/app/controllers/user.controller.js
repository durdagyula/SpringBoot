(function () {
    'use strict';

    angular.module('app').controller('UserController', UserController);

    UserController.$inject = ['$http','$scope','$location'];

    function UserController($http,$scope,$location){
        var vm = this;

        vm.users = [];
        $scope.currentUser = null;
        $scope.successmsg = '';
        $scope.errormsg = '';
        vm.getAll = getAll;
        vm.getAdmins = getAdmins;
        vm.deleteUser = deleteUser;
        vm.getCurrentUser = getCurrentUser;
        vm.addUser = addUser;
        vm.logout = logout;
        vm.login = login;
        vm.openModal=openModal;

        init();

        function init() {
            getCurrentUser();
            getAll();
        }

        function getAll() {
            var url = "/users/all/";
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

        function getCurrentUser() {
            var url = "/users/currentUser/";
            var userPromise = $http.get(url);
            userPromise.then(function (response) {
                $scope.currentUser = response.data;
            });
        }

        function logout() {
            var url = "/users/logout/";
            var userPromise = $http.get(url);
            userPromise.then(function (response) {
                $scope.currentUser = response.data;
                $location.path('/login')
            });
        }

        function login() {
            $location.path('/login');
        }
        
        function openModal() {
            $scope.addUser = null;
        }
        
        function addUser(addUser) {
            $scope.successmsg='';
            $scope.errormsg='';
            var url = "/users/add/" + addUser.name + '/' + addUser.credits + '/' + addUser.password + '/' + addUser.isAdmin;
            $http.post(url).then(function (response) {
                if(response.data){
                    getAll();
                    $scope.successmsg = 'User added successfully!';
                } else {
                    $scope.errormsg = 'User name is reserved!';
                }
            });
            
        }
    }
})();