(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('DevDrillingToolDeleteController',DevDrillingToolDeleteController);

    DevDrillingToolDeleteController.$inject = ['$uibModalInstance', 'entity', 'DevDrillingTool'];

    function DevDrillingToolDeleteController($uibModalInstance, entity, DevDrillingTool) {
        var vm = this;

        vm.devDrillingTool = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DevDrillingTool.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
