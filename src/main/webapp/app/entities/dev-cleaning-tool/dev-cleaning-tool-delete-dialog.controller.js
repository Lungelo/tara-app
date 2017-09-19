(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('DevCleaningToolDeleteController',DevCleaningToolDeleteController);

    DevCleaningToolDeleteController.$inject = ['$uibModalInstance', 'entity', 'DevCleaningTool'];

    function DevCleaningToolDeleteController($uibModalInstance, entity, DevCleaningTool) {
        var vm = this;

        vm.devCleaningTool = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DevCleaningTool.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
