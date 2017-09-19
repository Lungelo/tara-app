(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('StopChargingToolDialogController', StopChargingToolDialogController);

    StopChargingToolDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'StopChargingTool', 'Company', 'User'];

    function StopChargingToolDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, StopChargingTool, Company, User) {
        var vm = this;

        vm.stopChargingTool = entity;
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
            if (vm.stopChargingTool.id !== null) {
                StopChargingTool.update(vm.stopChargingTool, onSaveSuccess, onSaveError);
            } else {
                StopChargingTool.save(vm.stopChargingTool, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('taraApp:stopChargingToolUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setPhoto = function ($file, stopChargingTool) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        stopChargingTool.photo = base64Data;
                        stopChargingTool.photoContentType = $file.type;
                    });
                });
            }
        };

        vm.setDatasheet = function ($file, stopChargingTool) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        stopChargingTool.datasheet = base64Data;
                        stopChargingTool.datasheetContentType = $file.type;
                    });
                });
            }
        };

    }
})();
