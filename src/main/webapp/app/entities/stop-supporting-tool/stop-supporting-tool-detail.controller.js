(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('StopSupportingToolDetailController', StopSupportingToolDetailController);

    StopSupportingToolDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'StopSupportingTool', 'Company', 'User'];

    function StopSupportingToolDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, StopSupportingTool, Company, User) {
        var vm = this;

        vm.stopSupportingTool = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('taraApp:stopSupportingToolUpdate', function(event, result) {
            vm.stopSupportingTool = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
