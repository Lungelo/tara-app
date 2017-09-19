(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('StopCleaningToolDetailController', StopCleaningToolDetailController);

    StopCleaningToolDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'StopCleaningTool', 'Company', 'User'];

    function StopCleaningToolDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, StopCleaningTool, Company, User) {
        var vm = this;

        vm.stopCleaningTool = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('taraApp:stopCleaningToolUpdate', function(event, result) {
            vm.stopCleaningTool = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
