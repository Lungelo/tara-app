(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('GetWayDeleteController',GetWayDeleteController);

    GetWayDeleteController.$inject = ['$uibModalInstance', 'entity', 'GetWay'];

    function GetWayDeleteController($uibModalInstance, entity, GetWay) {
        var vm = this;

        vm.getWay = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            GetWay.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
