(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('DevChargingToolDeleteController',DevChargingToolDeleteController);

    DevChargingToolDeleteController.$inject = ['$uibModalInstance', 'entity', 'DevChargingTool'];

    function DevChargingToolDeleteController($uibModalInstance, entity, DevChargingTool) {
        var vm = this;

        vm.devChargingTool = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DevChargingTool.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
