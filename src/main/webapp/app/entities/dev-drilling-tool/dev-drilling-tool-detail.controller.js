(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('DevDrillingToolDetailController', DevDrillingToolDetailController);

    DevDrillingToolDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'DevDrillingTool', 'Company', 'User'];

    function DevDrillingToolDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, DevDrillingTool, Company, User) {
        var vm = this;

        vm.devDrillingTool = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('taraApp:devDrillingToolUpdate', function(event, result) {
            vm.devDrillingTool = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
