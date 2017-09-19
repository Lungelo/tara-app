(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('WirelessSensorNetworkDetailController', WirelessSensorNetworkDetailController);

    WirelessSensorNetworkDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'WirelessSensorNetwork', 'Company', 'User'];

    function WirelessSensorNetworkDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, WirelessSensorNetwork, Company, User) {
        var vm = this;

        vm.wirelessSensorNetwork = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('taraApp:wirelessSensorNetworkUpdate', function(event, result) {
            vm.wirelessSensorNetwork = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
