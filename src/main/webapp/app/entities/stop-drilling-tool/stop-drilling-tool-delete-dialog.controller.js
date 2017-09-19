(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('StopDrillingToolDeleteController',StopDrillingToolDeleteController);

    StopDrillingToolDeleteController.$inject = ['$uibModalInstance', 'entity', 'StopDrillingTool'];

    function StopDrillingToolDeleteController($uibModalInstance, entity, StopDrillingTool) {
        var vm = this;

        vm.stopDrillingTool = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            StopDrillingTool.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
