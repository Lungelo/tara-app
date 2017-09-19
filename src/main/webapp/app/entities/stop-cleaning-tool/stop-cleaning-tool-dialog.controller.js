(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('StopCleaningToolDialogController', StopCleaningToolDialogController);

    StopCleaningToolDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'StopCleaningTool', 'Company', 'User'];

    function StopCleaningToolDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, StopCleaningTool, Company, User) {
        var vm = this;

        vm.stopCleaningTool = entity;
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
            if (vm.stopCleaningTool.id !== null) {
                StopCleaningTool.update(vm.stopCleaningTool, onSaveSuccess, onSaveError);
            } else {
                StopCleaningTool.save(vm.stopCleaningTool, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('taraApp:stopCleaningToolUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setPhoto = function ($file, stopCleaningTool) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        stopCleaningTool.photo = base64Data;
                        stopCleaningTool.photoContentType = $file.type;
                    });
                });
            }
        };

        vm.setDatasheet = function ($file, stopCleaningTool) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        stopCleaningTool.datasheet = base64Data;
                        stopCleaningTool.datasheetContentType = $file.type;
                    });
                });
            }
        };

    }
})();
