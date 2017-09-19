(function() {
    'use strict';

    angular
        .module('taraApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('stop-cleaning-tool', {
            parent: 'entity',
            url: '/stop-cleaning-tool?page&sort&search',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'StopCleaningTools'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/stop-cleaning-tool/stop-cleaning-tools.html',
                    controller: 'StopCleaningToolController',
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
        .state('stop-cleaning-tool-detail', {
            parent: 'stop-cleaning-tool',
            url: '/stop-cleaning-tool/{id}',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'StopCleaningTool'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/stop-cleaning-tool/stop-cleaning-tool-detail.html',
                    controller: 'StopCleaningToolDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'StopCleaningTool', function($stateParams, StopCleaningTool) {
                    return StopCleaningTool.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'stop-cleaning-tool',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('stop-cleaning-tool-detail.edit', {
            parent: 'stop-cleaning-tool-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/stop-cleaning-tool/stop-cleaning-tool-dialog.html',
                    controller: 'StopCleaningToolDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['StopCleaningTool', function(StopCleaningTool) {
                            return StopCleaningTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('stop-cleaning-tool.new', {
            parent: 'stop-cleaning-tool',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/stop-cleaning-tool/stop-cleaning-tool-dialog.html',
                    controller: 'StopCleaningToolDialogController',
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
                                trammingCapacity: null,
                                testedTrammingCapacity: null,
                                height: null,
                                testedHeight: null,
                                weight: null,
                                testedWeight: null,
                                length: null,
                                testedLength: null,
                                rpmOutput: null,
                                testedRpmOutput: null,
                                torque: null,
                                testedTorque: null,
                                tankRange: null,
                                testedTankRange: null,
                                fuelConsumption: null,
                                testedFuelConsumption: null,
                                availability: null,
                                testedAvailability: null,
                                operatingCostPerTonne: null,
                                testedOperatingCostPerTonne: null,
                                fuelConsumptionPerTonneHauled: null,
                                testedFuelConsumptionPerTonneHauled: null,
                                controlSystem: null,
                                testedControlSystem: null,
                                cycleTime: null,
                                testedCycleTime: null,
                                turningRadiusInner: null,
                                testedTurningRadiusInner: null,
                                turningRadiusOuter: null,
                                testedTurningRadiusOuter: null,
                                lubricationType: null,
                                testedLubricationType: null,
                                temperatureAtAmbient: null,
                                testedTemperatureAtAmbient: null,
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
                    $state.go('stop-cleaning-tool', null, { reload: 'stop-cleaning-tool' });
                }, function() {
                    $state.go('stop-cleaning-tool');
                });
            }]
        })
        .state('stop-cleaning-tool.edit', {
            parent: 'stop-cleaning-tool',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/stop-cleaning-tool/stop-cleaning-tool-dialog.html',
                    controller: 'StopCleaningToolDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['StopCleaningTool', function(StopCleaningTool) {
                            return StopCleaningTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('stop-cleaning-tool', null, { reload: 'stop-cleaning-tool' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('stop-cleaning-tool.delete', {
            parent: 'stop-cleaning-tool',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/stop-cleaning-tool/stop-cleaning-tool-delete-dialog.html',
                    controller: 'StopCleaningToolDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['StopCleaningTool', function(StopCleaningTool) {
                            return StopCleaningTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('stop-cleaning-tool', null, { reload: 'stop-cleaning-tool' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
