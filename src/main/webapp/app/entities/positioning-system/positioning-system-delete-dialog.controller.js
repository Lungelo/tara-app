(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('PositioningSystemDeleteController',PositioningSystemDeleteController);

    PositioningSystemDeleteController.$inject = ['$uibModalInstance', 'entity', 'PositioningSystem'];

    function PositioningSystemDeleteController($uibModalInstance, entity, PositioningSystem) {
        var vm = this;

        vm.positioningSystem = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PositioningSystem.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
