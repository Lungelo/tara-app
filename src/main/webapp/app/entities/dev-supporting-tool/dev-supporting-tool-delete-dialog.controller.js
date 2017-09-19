(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('DevSupportingToolDeleteController',DevSupportingToolDeleteController);

    DevSupportingToolDeleteController.$inject = ['$uibModalInstance', 'entity', 'DevSupportingTool'];

    function DevSupportingToolDeleteController($uibModalInstance, entity, DevSupportingTool) {
        var vm = this;

        vm.devSupportingTool = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DevSupportingTool.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
