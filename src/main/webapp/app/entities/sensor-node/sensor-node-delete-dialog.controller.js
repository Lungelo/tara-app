(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('SensorNodeDeleteController',SensorNodeDeleteController);

    SensorNodeDeleteController.$inject = ['$uibModalInstance', 'entity', 'SensorNode'];

    function SensorNodeDeleteController($uibModalInstance, entity, SensorNode) {
        var vm = this;

        vm.sensorNode = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            SensorNode.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
