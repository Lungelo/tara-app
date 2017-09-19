(function() {
    'use strict';

    angular
        .module('taraApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('dev-drilling-tool', {
            parent: 'entity',
            url: '/dev-drilling-tool?page&sort&search',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'DevDrillingTools'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/dev-drilling-tool/dev-drilling-tools.html',
                    controller: 'DevDrillingToolController',
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
        .state('dev-drilling-tool-detail', {
            parent: 'dev-drilling-tool',
            url: '/dev-drilling-tool/{id}',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'DevDrillingTool'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/dev-drilling-tool/dev-drilling-tool-detail.html',
                    controller: 'DevDrillingToolDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'DevDrillingTool', function($stateParams, DevDrillingTool) {
                    return DevDrillingTool.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'dev-drilling-tool',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('dev-drilling-tool-detail.edit', {
            parent: 'dev-drilling-tool-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dev-drilling-tool/dev-drilling-tool-dialog.html',
                    controller: 'DevDrillingToolDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DevDrillingTool', function(DevDrillingTool) {
                            return DevDrillingTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('dev-drilling-tool.new', {
            parent: 'dev-drilling-tool',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dev-drilling-tool/dev-drilling-tool-dialog.html',
                    controller: 'DevDrillingToolDialogController',
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
                                weight: null,
                                testedWeight: null,
                                length: null,
                                testedLength: null,
                                drillingRate: null,
                                testedDrillingRate: null,
                                noiseLevel: null,
                                testedNoiseLevel: null,
                                settingUpTime: null,
                                testedSettingUpTime: null,
                                dismantlingTime: null,
                                testedDismantlingTime: null,
                                waterUsePerMetreDrilled: null,
                                testedWaterUsePerMetreDrilled: null,
                                availability: null,
                                testedAvailability: null,
                                costPerMeterDrilled: null,
                                testedCostPerMeterDrilled: null,
                                holeSizeRange: null,
                                testedHoleSizeRange: null,
                                powerSource: null,
                                testedPowerSource: null,
                                powerRating: null,
                                testedPowerRating: null,
                                cost: null,
                                testedCost: null,
                                trammingSpeed: null,
                                testedTrammingSpeed: null,
                                workingHeight: null,
                                testedWorkingHeight: null,
                                gradeability: null,
                                testedGradeability: null,
                                numberOfBoom: null,
                                testedNumberOfBoom: null,
                                typerOfBoom: null,
                                testedTyperOfBoom: null,
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
                    $state.go('dev-drilling-tool', null, { reload: 'dev-drilling-tool' });
                }, function() {
                    $state.go('dev-drilling-tool');
                });
            }]
        })
        .state('dev-drilling-tool.edit', {
            parent: 'dev-drilling-tool',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dev-drilling-tool/dev-drilling-tool-dialog.html',
                    controller: 'DevDrillingToolDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DevDrillingTool', function(DevDrillingTool) {
                            return DevDrillingTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('dev-drilling-tool', null, { reload: 'dev-drilling-tool' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('dev-drilling-tool.delete', {
            parent: 'dev-drilling-tool',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dev-drilling-tool/dev-drilling-tool-delete-dialog.html',
                    controller: 'DevDrillingToolDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DevDrillingTool', function(DevDrillingTool) {
                            return DevDrillingTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('dev-drilling-tool', null, { reload: 'dev-drilling-tool' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
