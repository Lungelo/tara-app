(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('StopChargingToolDetailController', StopChargingToolDetailController);

    StopChargingToolDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'StopChargingTool', 'Company', 'User'];

    function StopChargingToolDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, StopChargingTool, Company, User) {
        var vm = this;

        vm.stopChargingTool = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('taraApp:stopChargingToolUpdate', function(event, result) {
            vm.stopChargingTool = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
