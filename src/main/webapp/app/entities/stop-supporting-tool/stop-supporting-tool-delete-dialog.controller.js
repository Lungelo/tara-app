(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('StopSupportingToolDeleteController',StopSupportingToolDeleteController);

    StopSupportingToolDeleteController.$inject = ['$uibModalInstance', 'entity', 'StopSupportingTool'];

    function StopSupportingToolDeleteController($uibModalInstance, entity, StopSupportingTool) {
        var vm = this;

        vm.stopSupportingTool = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            StopSupportingTool.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
