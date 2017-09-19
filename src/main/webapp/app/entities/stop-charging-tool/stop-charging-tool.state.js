(function() {
    'use strict';

    angular
        .module('taraApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('stop-charging-tool', {
            parent: 'entity',
            url: '/stop-charging-tool?page&sort&search',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'StopChargingTools'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/stop-charging-tool/stop-charging-tools.html',
                    controller: 'StopChargingToolController',
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
        .state('stop-charging-tool-detail', {
            parent: 'stop-charging-tool',
            url: '/stop-charging-tool/{id}',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'StopChargingTool'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/stop-charging-tool/stop-charging-tool-detail.html',
                    controller: 'StopChargingToolDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'StopChargingTool', function($stateParams, StopChargingTool) {
                    return StopChargingTool.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'stop-charging-tool',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('stop-charging-tool-detail.edit', {
            parent: 'stop-charging-tool-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/stop-charging-tool/stop-charging-tool-dialog.html',
                    controller: 'StopChargingToolDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['StopChargingTool', function(StopChargingTool) {
                            return StopChargingTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('stop-charging-tool.new', {
            parent: 'stop-charging-tool',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/stop-charging-tool/stop-charging-tool-dialog.html',
                    controller: 'StopChargingToolDialogController',
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
                                hoseLength: null,
                                testedHoseLength: null,
                                holeDiameter: null,
                                testedHoleDiameter: null,
                                explosiveType: null,
                                testedExplosiveType: null,
                                TankCapacity: null,
                                testedTankCapacity: null,
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
                                fuelConsumptionPerExplosiveKgCharged: null,
                                testedFuelConsumptionPerExplosiveKgCharged: null,
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
                    $state.go('stop-charging-tool', null, { reload: 'stop-charging-tool' });
                }, function() {
                    $state.go('stop-charging-tool');
                });
            }]
        })
        .state('stop-charging-tool.edit', {
            parent: 'stop-charging-tool',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/stop-charging-tool/stop-charging-tool-dialog.html',
                    controller: 'StopChargingToolDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['StopChargingTool', function(StopChargingTool) {
                            return StopChargingTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('stop-charging-tool', null, { reload: 'stop-charging-tool' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('stop-charging-tool.delete', {
            parent: 'stop-charging-tool',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/stop-charging-tool/stop-charging-tool-delete-dialog.html',
                    controller: 'StopChargingToolDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['StopChargingTool', function(StopChargingTool) {
                            return StopChargingTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('stop-charging-tool', null, { reload: 'stop-charging-tool' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
