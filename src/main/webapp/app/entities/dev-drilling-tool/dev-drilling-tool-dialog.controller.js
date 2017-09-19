(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('DevDrillingToolDialogController', DevDrillingToolDialogController);

    DevDrillingToolDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'DevDrillingTool', 'Company', 'User'];

    function DevDrillingToolDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, DevDrillingTool, Company, User) {
        var vm = this;

        vm.devDrillingTool = entity;
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
            if (vm.devDrillingTool.id !== null) {
                DevDrillingTool.update(vm.devDrillingTool, onSaveSuccess, onSaveError);
            } else {
                DevDrillingTool.save(vm.devDrillingTool, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('taraApp:devDrillingToolUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setPhoto = function ($file, devDrillingTool) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        devDrillingTool.photo = base64Data;
                        devDrillingTool.photoContentType = $file.type;
                    });
                });
            }
        };

        vm.setDatasheet = function ($file, devDrillingTool) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        devDrillingTool.datasheet = base64Data;
                        devDrillingTool.datasheetContentType = $file.type;
                    });
                });
            }
        };

    }
})();
