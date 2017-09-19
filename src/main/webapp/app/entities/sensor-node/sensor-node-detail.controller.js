(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('SensorNodeDetailController', SensorNodeDetailController);

    SensorNodeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'SensorNode', 'Company', 'User'];

    function SensorNodeDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, SensorNode, Company, User) {
        var vm = this;

        vm.sensorNode = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('taraApp:sensorNodeUpdate', function(event, result) {
            vm.sensorNode = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
