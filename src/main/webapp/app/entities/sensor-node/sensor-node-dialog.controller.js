(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('SensorNodeDialogController', SensorNodeDialogController);

    SensorNodeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'SensorNode', 'Company', 'User'];

    function SensorNodeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, SensorNode, Company, User) {
        var vm = this;

        vm.sensorNode = entity;
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
            if (vm.sensorNode.id !== null) {
                SensorNode.update(vm.sensorNode, onSaveSuccess, onSaveError);
            } else {
                SensorNode.save(vm.sensorNode, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('taraApp:sensorNodeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setPhoto = function ($file, sensorNode) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        sensorNode.photo = base64Data;
                        sensorNode.photoContentType = $file.type;
                    });
                });
            }
        };

        vm.setDatasheet = function ($file, sensorNode) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        sensorNode.datasheet = base64Data;
                        sensorNode.datasheetContentType = $file.type;
                    });
                });
            }
        };

    }
})();
