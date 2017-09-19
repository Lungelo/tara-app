(function() {
    'use strict';

    angular
        .module('taraApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('get-way', {
            parent: 'entity',
            url: '/get-way?page&sort&search',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'GetWays'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/get-way/get-ways.html',
                    controller: 'GetWayController',
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
        .state('get-way-detail', {
            parent: 'get-way',
            url: '/get-way/{id}',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'GetWay'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/get-way/get-way-detail.html',
                    controller: 'GetWayDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'GetWay', function($stateParams, GetWay) {
                    return GetWay.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'get-way',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('get-way-detail.edit', {
            parent: 'get-way-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/get-way/get-way-dialog.html',
                    controller: 'GetWayDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['GetWay', function(GetWay) {
                            return GetWay.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('get-way.new', {
            parent: 'get-way',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/get-way/get-way-dialog.html',
                    controller: 'GetWayDialogController',
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
                                compatibility: null,
                                upgradeability: null,
                                dustProof: null,
                                waterOrCondensationProof: null,
                                temperature: null,
                                easeOfInstallation: null,
                                maintainability: null,
                                size: null,
                                ram: null,
                                storage: null,
                                operatingTemperature: null,
                                power: null,
                                relativeHumidity: null,
                                systemOnChip: null,
                                cloudPlatformIntegration: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('get-way', null, { reload: 'get-way' });
                }, function() {
                    $state.go('get-way');
                });
            }]
        })
        .state('get-way.edit', {
            parent: 'get-way',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/get-way/get-way-dialog.html',
                    controller: 'GetWayDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['GetWay', function(GetWay) {
                            return GetWay.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('get-way', null, { reload: 'get-way' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('get-way.delete', {
            parent: 'get-way',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/get-way/get-way-delete-dialog.html',
                    controller: 'GetWayDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['GetWay', function(GetWay) {
                            return GetWay.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('get-way', null, { reload: 'get-way' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
