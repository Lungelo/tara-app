(function() {
    'use strict';

    angular
        .module('taraApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('stop-supporting-tool', {
            parent: 'entity',
            url: '/stop-supporting-tool?page&sort&search',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'StopSupportingTools'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/stop-supporting-tool/stop-supporting-tools.html',
                    controller: 'StopSupportingToolController',
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
        .state('stop-supporting-tool-detail', {
            parent: 'stop-supporting-tool',
            url: '/stop-supporting-tool/{id}',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'StopSupportingTool'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/stop-supporting-tool/stop-supporting-tool-detail.html',
                    controller: 'StopSupportingToolDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'StopSupportingTool', function($stateParams, StopSupportingTool) {
                    return StopSupportingTool.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'stop-supporting-tool',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('stop-supporting-tool-detail.edit', {
            parent: 'stop-supporting-tool-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/stop-supporting-tool/stop-supporting-tool-dialog.html',
                    controller: 'StopSupportingToolDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['StopSupportingTool', function(StopSupportingTool) {
                            return StopSupportingTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('stop-supporting-tool.new', {
            parent: 'stop-supporting-tool',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/stop-supporting-tool/stop-supporting-tool-dialog.html',
                    controller: 'StopSupportingToolDialogController',
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
                                holeSize: null,
                                testedHoleSize: null,
                                drillWaterConsumption: null,
                                testedDrillWaterConsumption: null,
                                cycleTimeBolting: null,
                                testedCycleTimeBolting: null,
                                waterConsumptionPerMetreDrilled: null,
                                testedWaterConsumptionPerMetreDrilled: null,
                                powerSource: null,
                                testedPowerSource: null,
                                trammingSpeed: null,
                                testedTrammingSpeed: null,
                                boltLengthRange: null,
                                testedBoltLengthRange: null,
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
                                innerTurningRadius: null,
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
                    $state.go('stop-supporting-tool', null, { reload: 'stop-supporting-tool' });
                }, function() {
                    $state.go('stop-supporting-tool');
                });
            }]
        })
        .state('stop-supporting-tool.edit', {
            parent: 'stop-supporting-tool',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/stop-supporting-tool/stop-supporting-tool-dialog.html',
                    controller: 'StopSupportingToolDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['StopSupportingTool', function(StopSupportingTool) {
                            return StopSupportingTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('stop-supporting-tool', null, { reload: 'stop-supporting-tool' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('stop-supporting-tool.delete', {
            parent: 'stop-supporting-tool',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/stop-supporting-tool/stop-supporting-tool-delete-dialog.html',
                    controller: 'StopSupportingToolDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['StopSupportingTool', function(StopSupportingTool) {
                            return StopSupportingTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('stop-supporting-tool', null, { reload: 'stop-supporting-tool' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
