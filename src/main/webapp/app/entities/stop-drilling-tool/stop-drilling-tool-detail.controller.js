(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('StopDrillingToolDetailController', StopDrillingToolDetailController);

    StopDrillingToolDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'StopDrillingTool', 'Company', 'User'];

    function StopDrillingToolDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, StopDrillingTool, Company, User) {
        var vm = this;

        vm.stopDrillingTool = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('taraApp:stopDrillingToolUpdate', function(event, result) {
            vm.stopDrillingTool = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
