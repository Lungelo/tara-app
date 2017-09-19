(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('SensorDialogController', SensorDialogController);

    SensorDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Sensor', 'Company', 'User'];

    function SensorDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Sensor, Company, User) {
        var vm = this;

        vm.sensor = entity;
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
            if (vm.sensor.id !== null) {
                Sensor.update(vm.sensor, onSaveSuccess, onSaveError);
            } else {
                Sensor.save(vm.sensor, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('taraApp:sensorUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setPhoto = function ($file, sensor) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        sensor.photo = base64Data;
                        sensor.photoContentType = $file.type;
                    });
                });
            }
        };

        vm.setDatasheet = function ($file, sensor) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        sensor.datasheet = base64Data;
                        sensor.datasheetContentType = $file.type;
                    });
                });
            }
        };

    }
})();
