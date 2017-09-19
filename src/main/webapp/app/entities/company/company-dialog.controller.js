(function() {
    'use strict';

    angular
        .module('taraApp')
        .controller('CompanyDialogController', CompanyDialogController);

    CompanyDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Company'];

    function CompanyDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Company) {
        var vm = this;

        vm.company = entity;
        vm.clear = clear;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.company.id !== null) {
                Company.update(vm.company, onSaveSuccess, onSaveError);
            } else {
                Company.save(vm.company, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('taraApp:companyUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setPhoto = function ($file, company) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        company.photo = base64Data;
                        company.photoContentType = $file.type;
                    });
                });
            }
        };

    }
})();
