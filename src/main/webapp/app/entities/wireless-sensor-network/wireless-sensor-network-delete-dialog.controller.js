(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('WirelessSensorNetworkDeleteController',WirelessSensorNetworkDeleteController);

    WirelessSensorNetworkDeleteController.$inject = ['$uibModalInstance', 'entity', 'WirelessSensorNetwork'];

    function WirelessSensorNetworkDeleteController($uibModalInstance, entity, WirelessSensorNetwork) {
        var vm = this;

        vm.wirelessSensorNetwork = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            WirelessSensorNetwork.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
