(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('UserExtraController', UserExtraController);

    UserExtraController.$inject = ['UserExtra', 'UserExtraSearch'];

    function UserExtraController(UserExtra, UserExtraSearch) {

        var vm = this;

        vm.userExtras = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            UserExtra.query(function(result) {
                vm.userExtras = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            UserExtraSearch.query({query: vm.searchQuery}, function(result) {
                vm.userExtras = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
