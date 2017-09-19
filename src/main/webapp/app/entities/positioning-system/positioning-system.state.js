(function() {
    'use strict';

    angular
        .module('taraApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('positioning-system', {
            parent: 'entity',
            url: '/positioning-system?page&sort&search',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'PositioningSystems'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/positioning-system/positioning-systems.html',
                    controller: 'PositioningSystemController',
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
        .state('positioning-system-detail', {
            parent: 'positioning-system',
            url: '/positioning-system/{id}',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'PositioningSystem'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/positioning-system/positioning-system-detail.html',
                    controller: 'PositioningSystemDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'PositioningSystem', function($stateParams, PositioningSystem) {
                    return PositioningSystem.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'positioning-system',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('positioning-system-detail.edit', {
            parent: 'positioning-system-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/positioning-system/positioning-system-dialog.html',
                    controller: 'PositioningSystemDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PositioningSystem', function(PositioningSystem) {
                            return PositioningSystem.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('positioning-system.new', {
            parent: 'positioning-system',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/positioning-system/positioning-system-dialog.html',
                    controller: 'PositioningSystemDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                technologyType: null,
                                trl: null,
                                photo: null,
                                photoContentType: null,
                                datasheet: null,
                                datasheetContentType: null,
                                accuracy: null,
                                coverageArea: null,
                                cost: null,
                                requiredInfrastructure: null,
                                marketMaturity: null,
                                outputData: null,
                                privacy: null,
                                updateRate: null,
                                systemLatency: null,
                                _interface: null,
                                systemIntegrity: null,
                                robustness: null,
                                availability: null,
                                continuity: null,
                                scalability: null,
                                numberOfUsers: null,
                                approval: null,
                                levelOfHybridisation: null,
                                technology: null,
                                measuredQuantity: null,
                                basicMeasuringPrinciple: null,
                                positionAlgorithm: null,
                                signaltype: null,
                                signalWavelength: null,
                                systemArchitecture: null,
                                application: null,
                                coordinateReference: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('positioning-system', null, { reload: 'positioning-system' });
                }, function() {
                    $state.go('positioning-system');
                });
            }]
        })
        .state('positioning-system.edit', {
            parent: 'positioning-system',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/positioning-system/positioning-system-dialog.html',
                    controller: 'PositioningSystemDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PositioningSystem', function(PositioningSystem) {
                            return PositioningSystem.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('positioning-system', null, { reload: 'positioning-system' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('positioning-system.delete', {
            parent: 'positioning-system',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/positioning-system/positioning-system-delete-dialog.html',
                    controller: 'PositioningSystemDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['PositioningSystem', function(PositioningSystem) {
                            return PositioningSystem.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('positioning-system', null, { reload: 'positioning-system' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
