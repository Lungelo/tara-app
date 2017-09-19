(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('GetWayDetailController', GetWayDetailController);

    GetWayDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'GetWay', 'Company', 'User'];

    function GetWayDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, GetWay, Company, User) {
        var vm = this;

        vm.getWay = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('taraApp:getWayUpdate', function(event, result) {
            vm.getWay = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
