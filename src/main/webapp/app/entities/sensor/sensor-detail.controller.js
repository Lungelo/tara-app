(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('SensorDetailController', SensorDetailController);

    SensorDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Sensor', 'Company', 'User'];

    function SensorDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Sensor, Company, User) {
        var vm = this;

        vm.sensor = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('taraApp:sensorUpdate', function(event, result) {
            vm.sensor = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
