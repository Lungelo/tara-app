(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('WirelessSensorNetworkDialogController', WirelessSensorNetworkDialogController);

    WirelessSensorNetworkDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'WirelessSensorNetwork', 'Company', 'User'];

    function WirelessSensorNetworkDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, WirelessSensorNetwork, Company, User) {
        var vm = this;

        vm.wirelessSensorNetwork = entity;
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
            if (vm.wirelessSensorNetwork.id !== null) {
                WirelessSensorNetwork.update(vm.wirelessSensorNetwork, onSaveSuccess, onSaveError);
            } else {
                WirelessSensorNetwork.save(vm.wirelessSensorNetwork, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('taraApp:wirelessSensorNetworkUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setPhoto = function ($file, wirelessSensorNetwork) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        wirelessSensorNetwork.photo = base64Data;
                        wirelessSensorNetwork.photoContentType = $file.type;
                    });
                });
            }
        };

        vm.setDatasheet = function ($file, wirelessSensorNetwork) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        wirelessSensorNetwork.datasheet = base64Data;
                        wirelessSensorNetwork.datasheetContentType = $file.type;
                    });
                });
            }
        };

    }
})();
