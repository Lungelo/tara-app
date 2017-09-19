(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('StopCleaningToolDeleteController',StopCleaningToolDeleteController);

    StopCleaningToolDeleteController.$inject = ['$uibModalInstance', 'entity', 'StopCleaningTool'];

    function StopCleaningToolDeleteController($uibModalInstance, entity, StopCleaningTool) {
        var vm = this;

        vm.stopCleaningTool = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            StopCleaningTool.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
