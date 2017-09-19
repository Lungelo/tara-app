(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('PositioningSystemDetailController', PositioningSystemDetailController);

    PositioningSystemDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'PositioningSystem', 'Company', 'User'];

    function PositioningSystemDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, PositioningSystem, Company, User) {
        var vm = this;

        vm.positioningSystem = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('taraApp:positioningSystemUpdate', function(event, result) {
            vm.positioningSystem = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
