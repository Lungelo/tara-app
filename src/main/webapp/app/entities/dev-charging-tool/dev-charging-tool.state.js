(function() {
    'use strict';

    angular
        .module('taraApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('dev-charging-tool', {
            parent: 'entity',
            url: '/dev-charging-tool?page&sort&search',
            data: {
            	
                authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'DevChargingTools'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/dev-charging-tool/dev-charging-tools.html',
                    controller: 'DevChargingToolController',
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
        .state('dev-charging-tool-detail', {
            parent: 'dev-charging-tool',
            url: '/dev-charging-tool/{id}',
            data: {
                authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'DevChargingTool'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/dev-charging-tool/dev-charging-tool-detail.html',
                    controller: 'DevChargingToolDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'DevChargingTool', function($stateParams, DevChargingTool) {
                    return DevChargingTool.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'dev-charging-tool',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('dev-charging-tool-detail.edit', {
            parent: 'dev-charging-tool-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dev-charging-tool/dev-charging-tool-dialog.html',
                    controller: 'DevChargingToolDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DevChargingTool', function(DevChargingTool) {
                            return DevChargingTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('dev-charging-tool.new', {
            parent: 'dev-charging-tool',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dev-charging-tool/dev-charging-tool-dialog.html',
                    controller: 'DevChargingToolDialogController',
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
                                tesdedExplosiveType: null,
                                tankCapacity: null,
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
                    $state.go('dev-charging-tool', null, { reload: 'dev-charging-tool' });
                }, function() {
                    $state.go('dev-charging-tool');
                });
            }]
        })
        .state('dev-charging-tool.edit', {
            parent: 'dev-charging-tool',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dev-charging-tool/dev-charging-tool-dialog.html',
                    controller: 'DevChargingToolDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DevChargingTool', function(DevChargingTool) {
                            return DevChargingTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('dev-charging-tool', null, { reload: 'dev-charging-tool' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('dev-charging-tool.delete', {
            parent: 'dev-charging-tool',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dev-charging-tool/dev-charging-tool-delete-dialog.html',
                    controller: 'DevChargingToolDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DevChargingTool', function(DevChargingTool) {
                            return DevChargingTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('dev-charging-tool', null, { reload: 'dev-charging-tool' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
