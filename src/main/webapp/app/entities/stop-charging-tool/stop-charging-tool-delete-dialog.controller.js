(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('StopChargingToolDeleteController',StopChargingToolDeleteController);

    StopChargingToolDeleteController.$inject = ['$uibModalInstance', 'entity', 'StopChargingTool'];

    function StopChargingToolDeleteController($uibModalInstance, entity, StopChargingTool) {
        var vm = this;

        vm.stopChargingTool = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            StopChargingTool.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
