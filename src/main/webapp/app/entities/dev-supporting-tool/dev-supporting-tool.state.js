(function() {
    'use strict';

    angular
        .module('taraApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('dev-supporting-tool', {
            parent: 'entity',
            url: '/dev-supporting-tool?page&sort&search',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'DevSupportingTools'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/dev-supporting-tool/dev-supporting-tools.html',
                    controller: 'DevSupportingToolController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
            }
        })
        .state('dev-supporting-tool-detail', {
            parent: 'dev-supporting-tool',
            url: '/dev-supporting-tool/{id}',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'DevSupportingTool'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/dev-supporting-tool/dev-supporting-tool-detail.html',
                    controller: 'DevSupportingToolDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'DevSupportingTool', function($stateParams, DevSupportingTool) {
                    return DevSupportingTool.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'dev-supporting-tool',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('dev-supporting-tool-detail.edit', {
            parent: 'dev-supporting-tool-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dev-supporting-tool/dev-supporting-tool-dialog.html',
                    controller: 'DevSupportingToolDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DevSupportingTool', function(DevSupportingTool) {
                            return DevSupportingTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('dev-supporting-tool.new', {
            parent: 'dev-supporting-tool',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dev-supporting-tool/dev-supporting-tool-dialog.html',
                    controller: 'DevSupportingToolDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                model: null,
                                technologyType: null,
                                trl: null,
                                photo: null,
                                photoContentType: null,
                                datasheet: null,
                                datasheetContentType: null,
                                typeOfMachine: null,
                                testedTypeOfMachine: null,
                                weight: null,
                                testedWeight: null,
                                length: null,
                                testedLength: null,
                                width: null,
                                testedWidth: null,
                                powerRatingPercussion: null,
                                testedPowerRatingPercussion: null,
                                noiseLevelAtOperatorStand: null,
                                testedNoiseLevelAtOperatorStand: null,
                                holeSizeRange: null,
                                testedHoleSizeRange: null,
                                drillWaterConsumption: null,
                                testedDrillWaterConsumption: null,
                                waterConsumptionPerMetreDrilled: null,
                                testedWaterConsumptionPerMetreDrilled: null,
                                powerSource: null,
                                testedPowerSource: null,
                                trammingSpeed: null,
                                testedTrammingSpeed: null,
                                boltLengthRange: null,
                                testedBoltLengthRange: null,
                                minimumOperatingHeight: null,
                                testedMinimumOperatingHeight: null,
                                drillSpeed: null,
                                testedDrillSpeed: null,
                                gradeability: null,
                                testedGradeability: null,
                                numberOfBooms: null,
                                testedNumberOfBooms: null,
                                typeOfBoom: null,
                                testedTypeOfBoom: null,
                                outerTurningRadius: null,
                                testedOuterTurningRadius: null,
                                nnerTurningRadius: null,
                                testedInnerTurningRadius: null,
                                observations1: null,
                                testedObservations1: null,
                                observations2: null,
                                testedObservations2: null,
                                observations3: null,
                                testedObservations3: null,
                                observations4: null,
                                testedObservations4: null,
                                observations5: null,
                                testedObservations5: null,
                                observations6: null,
                                testedObservations6: null,
                                operators_Comments: null,
                                testedOperators_Comments: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('dev-supporting-tool', null, { reload: 'dev-supporting-tool' });
                }, function() {
                    $state.go('dev-supporting-tool');
                });
            }]
        })
        .state('dev-supporting-tool.edit', {
            parent: 'dev-supporting-tool',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dev-supporting-tool/dev-supporting-tool-dialog.html',
                    controller: 'DevSupportingToolDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DevSupportingTool', function(DevSupportingTool) {
                            return DevSupportingTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('dev-supporting-tool', null, { reload: 'dev-supporting-tool' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('dev-supporting-tool.delete', {
            parent: 'dev-supporting-tool',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dev-supporting-tool/dev-supporting-tool-delete-dialog.html',
                    controller: 'DevSupportingToolDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DevSupportingTool', function(DevSupportingTool) {
                            return DevSupportingTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('dev-supporting-tool', null, { reload: 'dev-supporting-tool' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
