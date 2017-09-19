(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('UserExtraDetailController', UserExtraDetailController);

    UserExtraDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UserExtra', 'Company', 'User'];

    function UserExtraDetailController($scope, $rootScope, $stateParams, previousState, entity, UserExtra, Company, User) {
        var vm = this;

        vm.userExtra = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('taraApp:userExtraUpdate', function(event, result) {
            vm.userExtra = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
