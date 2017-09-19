(function() {
    'use strict';

    angular
        .module('taraApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('sensor-node', {
            parent: 'entity',
            url: '/sensor-node?page&sort&search',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'SensorNodes'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/sensor-node/sensor-nodes.html',
                    controller: 'SensorNodeController',
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
        .state('sensor-node-detail', {
            parent: 'sensor-node',
            url: '/sensor-node/{id}',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'SensorNode'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/sensor-node/sensor-node-detail.html',
                    controller: 'SensorNodeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'SensorNode', function($stateParams, SensorNode) {
                    return SensorNode.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'sensor-node',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('sensor-node-detail.edit', {
            parent: 'sensor-node-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sensor-node/sensor-node-dialog.html',
                    controller: 'SensorNodeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['SensorNode', function(SensorNode) {
                            return SensorNode.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('sensor-node.new', {
            parent: 'sensor-node',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sensor-node/sensor-node-dialog.html',
                    controller: 'SensorNodeDialogController',
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
                                size: null,
                                cost: null,
                                bandwidth: null,
                                dataRate: null,
                                flashMemory: null,
                                ram: null,
                                energyUsage: null,
                                sleepEnergy: null,
                                dutyCycle: null,
                                frequency: null,
                                range: null,
                                mobility: null,
                                resilience: null,
                                selfTesting: null,
                                selfCalibration: null,
                                selfRepair: null,
                                transmissionPower: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('sensor-node', null, { reload: 'sensor-node' });
                }, function() {
                    $state.go('sensor-node');
                });
            }]
        })
        .state('sensor-node.edit', {
            parent: 'sensor-node',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sensor-node/sensor-node-dialog.html',
                    controller: 'SensorNodeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['SensorNode', function(SensorNode) {
                            return SensorNode.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('sensor-node', null, { reload: 'sensor-node' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('sensor-node.delete', {
            parent: 'sensor-node',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sensor-node/sensor-node-delete-dialog.html',
                    controller: 'SensorNodeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['SensorNode', function(SensorNode) {
                            return SensorNode.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('sensor-node', null, { reload: 'sensor-node' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
