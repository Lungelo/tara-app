(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('DevCleaningToolDetailController', DevCleaningToolDetailController);

    DevCleaningToolDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'DevCleaningTool', 'Company', 'User'];

    function DevCleaningToolDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, DevCleaningTool, Company, User) {
        var vm = this;

        vm.devCleaningTool = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('taraApp:devCleaningToolUpdate', function(event, result) {
            vm.devCleaningTool = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
