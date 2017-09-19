(function() {
    'use strict';

    angular
        .module('taraApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('wireless-sensor-network', {
            parent: 'entity',
            url: '/wireless-sensor-network?page&sort&search',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'WirelessSensorNetworks'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/wireless-sensor-network/wireless-sensor-networks.html',
                    controller: 'WirelessSensorNetworkController',
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
        .state('wireless-sensor-network-detail', {
            parent: 'wireless-sensor-network',
            url: '/wireless-sensor-network/{id}',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'WirelessSensorNetwork'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/wireless-sensor-network/wireless-sensor-network-detail.html',
                    controller: 'WirelessSensorNetworkDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'WirelessSensorNetwork', function($stateParams, WirelessSensorNetwork) {
                    return WirelessSensorNetwork.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'wireless-sensor-network',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('wireless-sensor-network-detail.edit', {
            parent: 'wireless-sensor-network-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/wireless-sensor-network/wireless-sensor-network-dialog.html',
                    controller: 'WirelessSensorNetworkDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['WirelessSensorNetwork', function(WirelessSensorNetwork) {
                            return WirelessSensorNetwork.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('wireless-sensor-network.new', {
            parent: 'wireless-sensor-network',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/wireless-sensor-network/wireless-sensor-network-dialog.html',
                    controller: 'WirelessSensorNetworkDialogController',
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
                                power: null,
                                operatingSystem: null,
                                protocol: null,
                                cost: null,
                                security: null,
                                selfOrganisationOfNodes: null,
                                mobility: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('wireless-sensor-network', null, { reload: 'wireless-sensor-network' });
                }, function() {
                    $state.go('wireless-sensor-network');
                });
            }]
        })
        .state('wireless-sensor-network.edit', {
            parent: 'wireless-sensor-network',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/wireless-sensor-network/wireless-sensor-network-dialog.html',
                    controller: 'WirelessSensorNetworkDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['WirelessSensorNetwork', function(WirelessSensorNetwork) {
                            return WirelessSensorNetwork.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('wireless-sensor-network', null, { reload: 'wireless-sensor-network' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('wireless-sensor-network.delete', {
            parent: 'wireless-sensor-network',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/wireless-sensor-network/wireless-sensor-network-delete-dialog.html',
                    controller: 'WirelessSensorNetworkDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['WirelessSensorNetwork', function(WirelessSensorNetwork) {
                            return WirelessSensorNetwork.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('wireless-sensor-network', null, { reload: 'wireless-sensor-network' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
