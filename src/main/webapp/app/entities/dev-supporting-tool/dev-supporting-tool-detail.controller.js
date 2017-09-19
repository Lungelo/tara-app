(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('DevSupportingToolDetailController', DevSupportingToolDetailController);

    DevSupportingToolDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'DevSupportingTool', 'Company', 'User'];

    function DevSupportingToolDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, DevSupportingTool, Company, User) {
        var vm = this;

        vm.devSupportingTool = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('taraApp:devSupportingToolUpdate', function(event, result) {
            vm.devSupportingTool = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
