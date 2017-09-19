(function() {
    'use strict';

    angular
        .module('taraApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('dev-cleaning-tool', {
            parent: 'entity',
            url: '/dev-cleaning-tool?page&sort&search',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'DevCleaningTools'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/dev-cleaning-tool/dev-cleaning-tools.html',
                    controller: 'DevCleaningToolController',
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
        .state('dev-cleaning-tool-detail', {
            parent: 'dev-cleaning-tool',
            url: '/dev-cleaning-tool/{id}',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'DevCleaningTool'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/dev-cleaning-tool/dev-cleaning-tool-detail.html',
                    controller: 'DevCleaningToolDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'DevCleaningTool', function($stateParams, DevCleaningTool) {
                    return DevCleaningTool.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'dev-cleaning-tool',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('dev-cleaning-tool-detail.edit', {
            parent: 'dev-cleaning-tool-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dev-cleaning-tool/dev-cleaning-tool-dialog.html',
                    controller: 'DevCleaningToolDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DevCleaningTool', function(DevCleaningTool) {
                            return DevCleaningTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('dev-cleaning-tool.new', {
            parent: 'dev-cleaning-tool',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dev-cleaning-tool/dev-cleaning-tool-dialog.html',
                    controller: 'DevCleaningToolDialogController',
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
                                testFuelConsumptionPerTonneHauled: null,
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
                    $state.go('dev-cleaning-tool', null, { reload: 'dev-cleaning-tool' });
                }, function() {
                    $state.go('dev-cleaning-tool');
                });
            }]
        })
        .state('dev-cleaning-tool.edit', {
            parent: 'dev-cleaning-tool',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dev-cleaning-tool/dev-cleaning-tool-dialog.html',
                    controller: 'DevCleaningToolDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DevCleaningTool', function(DevCleaningTool) {
                            return DevCleaningTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('dev-cleaning-tool', null, { reload: 'dev-cleaning-tool' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('dev-cleaning-tool.delete', {
            parent: 'dev-cleaning-tool',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dev-cleaning-tool/dev-cleaning-tool-delete-dialog.html',
                    controller: 'DevCleaningToolDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DevCleaningTool', function(DevCleaningTool) {
                            return DevCleaningTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('dev-cleaning-tool', null, { reload: 'dev-cleaning-tool' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
