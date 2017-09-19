(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('PositioningSystemDialogController', PositioningSystemDialogController);

    PositioningSystemDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'PositioningSystem', 'Company', 'User'];

    function PositioningSystemDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, PositioningSystem, Company, User) {
        var vm = this;

        vm.positioningSystem = entity;
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
            if (vm.positioningSystem.id !== null) {
                PositioningSystem.update(vm.positioningSystem, onSaveSuccess, onSaveError);
            } else {
                PositioningSystem.save(vm.positioningSystem, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('taraApp:positioningSystemUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setPhoto = function ($file, positioningSystem) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        positioningSystem.photo = base64Data;
                        positioningSystem.photoContentType = $file.type;
                    });
                });
            }
        };

        vm.setDatasheet = function ($file, positioningSystem) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        positioningSystem.datasheet = base64Data;
                        positioningSystem.datasheetContentType = $file.type;
                    });
                });
            }
        };

    }
})();
