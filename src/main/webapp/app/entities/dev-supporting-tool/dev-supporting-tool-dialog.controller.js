(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('DevSupportingToolDialogController', DevSupportingToolDialogController);

    DevSupportingToolDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'DevSupportingTool', 'Company', 'User'];

    function DevSupportingToolDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, DevSupportingTool, Company, User) {
        var vm = this;

        vm.devSupportingTool = entity;
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
            if (vm.devSupportingTool.id !== null) {
                DevSupportingTool.update(vm.devSupportingTool, onSaveSuccess, onSaveError);
            } else {
                DevSupportingTool.save(vm.devSupportingTool, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('taraApp:devSupportingToolUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setPhoto = function ($file, devSupportingTool) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        devSupportingTool.photo = base64Data;
                        devSupportingTool.photoContentType = $file.type;
                    });
                });
            }
        };

        vm.setDatasheet = function ($file, devSupportingTool) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        devSupportingTool.datasheet = base64Data;
                        devSupportingTool.datasheetContentType = $file.type;
                    });
                });
            }
        };

    }
})();
