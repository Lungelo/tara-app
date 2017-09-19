(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('StopSupportingToolDialogController', StopSupportingToolDialogController);

    StopSupportingToolDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'StopSupportingTool', 'Company', 'User'];

    function StopSupportingToolDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, StopSupportingTool, Company, User) {
        var vm = this;

        vm.stopSupportingTool = entity;
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
            if (vm.stopSupportingTool.id !== null) {
                StopSupportingTool.update(vm.stopSupportingTool, onSaveSuccess, onSaveError);
            } else {
                StopSupportingTool.save(vm.stopSupportingTool, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('taraApp:stopSupportingToolUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setPhoto = function ($file, stopSupportingTool) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        stopSupportingTool.photo = base64Data;
                        stopSupportingTool.photoContentType = $file.type;
                    });
                });
            }
        };

        vm.setDatasheet = function ($file, stopSupportingTool) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        stopSupportingTool.datasheet = base64Data;
                        stopSupportingTool.datasheetContentType = $file.type;
                    });
                });
            }
        };

    }
})();
