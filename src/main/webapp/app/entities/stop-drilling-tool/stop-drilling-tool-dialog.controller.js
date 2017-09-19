(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('StopDrillingToolDialogController', StopDrillingToolDialogController);

    StopDrillingToolDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'StopDrillingTool', 'Company', 'User'];

    function StopDrillingToolDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, StopDrillingTool, Company, User) {
        var vm = this;

        vm.stopDrillingTool = entity;
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
            if (vm.stopDrillingTool.id !== null) {
                StopDrillingTool.update(vm.stopDrillingTool, onSaveSuccess, onSaveError);
            } else {
                StopDrillingTool.save(vm.stopDrillingTool, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('taraApp:stopDrillingToolUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setPhoto = function ($file, stopDrillingTool) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        stopDrillingTool.photo = base64Data;
                        stopDrillingTool.photoContentType = $file.type;
                    });
                });
            }
        };

        vm.setDatasheet = function ($file, stopDrillingTool) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        stopDrillingTool.datasheet = base64Data;
                        stopDrillingTool.datasheetContentType = $file.type;
                    });
                });
            }
        };

    }
})();
