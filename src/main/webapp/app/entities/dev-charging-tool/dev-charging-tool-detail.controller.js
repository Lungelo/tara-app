(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('DevChargingToolDetailController', DevChargingToolDetailController);

    DevChargingToolDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'DevChargingTool', 'Company', 'User'];

    function DevChargingToolDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, DevChargingTool, Company, User) {
        var vm = this;

        vm.devChargingTool = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('taraApp:devChargingToolUpdate', function(event, result) {
            vm.devChargingTool = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
