(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('GetWayDialogController', GetWayDialogController);

    GetWayDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'GetWay', 'Company', 'User'];

    function GetWayDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, GetWay, Company, User) {
        var vm = this;

        vm.getWay = entity;
        vm.clear = clear;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;
        vm.companies = Company.query();
        vm.users = User.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.getWay.id !== null) {
                GetWay.update(vm.getWay, onSaveSuccess, onSaveError);
            } else {
                GetWay.save(vm.getWay, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('taraApp:getWayUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setPhoto = function ($file, getWay) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        getWay.photo = base64Data;
                        getWay.photoContentType = $file.type;
                    });
                });
            }
        };

        vm.setDatasheet = function ($file, getWay) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        getWay.datasheet = base64Data;
                        getWay.datasheetContentType = $file.type;
                    });
                });
            }
        };

    }
})();
