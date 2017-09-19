(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('DevChargingToolDialogController', DevChargingToolDialogController);

    DevChargingToolDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'DevChargingTool', 'Company', 'User'];

    function DevChargingToolDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, DevChargingTool, Company, User) {
        var vm = this;

        vm.devChargingTool = entity;
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
            if (vm.devChargingTool.id !== null) {
                DevChargingTool.update(vm.devChargingTool, onSaveSuccess, onSaveError);
            } else {
                DevChargingTool.save(vm.devChargingTool, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('taraApp:devChargingToolUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setPhoto = function ($file, devChargingTool) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        devChargingTool.photo = base64Data;
                        devChargingTool.photoContentType = $file.type;
                    });
                });
            }
        };

        vm.setDatasheet = function ($file, devChargingTool) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        devChargingTool.datasheet = base64Data;
                        devChargingTool.datasheetContentType = $file.type;
                    });
                });
            }
        };

    }
})();
