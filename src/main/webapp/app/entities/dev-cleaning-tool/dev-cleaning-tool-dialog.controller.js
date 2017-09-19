(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('DevCleaningToolDialogController', DevCleaningToolDialogController);

    DevCleaningToolDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'DevCleaningTool', 'Company', 'User'];

    function DevCleaningToolDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, DevCleaningTool, Company, User) {
        var vm = this;

        vm.devCleaningTool = entity;
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
            if (vm.devCleaningTool.id !== null) {
                DevCleaningTool.update(vm.devCleaningTool, onSaveSuccess, onSaveError);
            } else {
                DevCleaningTool.save(vm.devCleaningTool, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('taraApp:devCleaningToolUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setPhoto = function ($file, devCleaningTool) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        devCleaningTool.photo = base64Data;
                        devCleaningTool.photoContentType = $file.type;
                    });
                });
            }
        };

        vm.setDatasheet = function ($file, devCleaningTool) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        devCleaningTool.datasheet = base64Data;
                        devCleaningTool.datasheetContentType = $file.type;
                    });
                });
            }
        };

    }
})();
