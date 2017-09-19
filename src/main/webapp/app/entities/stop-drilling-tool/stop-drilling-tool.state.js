(function() {
    'use strict';

    angular
        .module('taraApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('stop-drilling-tool', {
            parent: 'entity',
            url: '/stop-drilling-tool?page&sort&search',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'StopDrillingTools'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/stop-drilling-tool/stop-drilling-tools.html',
                    controller: 'StopDrillingToolController',
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
        .state('stop-drilling-tool-detail', {
            parent: 'stop-drilling-tool',
            url: '/stop-drilling-tool/{id}',
            data: {
            	authorities: ['ROLE_USER','GENERAL_USER'],
                pageTitle: 'StopDrillingTool'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/stop-drilling-tool/stop-drilling-tool-detail.html',
                    controller: 'StopDrillingToolDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'StopDrillingTool', function($stateParams, StopDrillingTool) {
                    return StopDrillingTool.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'stop-drilling-tool',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('stop-drilling-tool-detail.edit', {
            parent: 'stop-drilling-tool-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/stop-drilling-tool/stop-drilling-tool-dialog.html',
                    controller: 'StopDrillingToolDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['StopDrillingTool', function(StopDrillingTool) {
                            return StopDrillingTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('stop-drilling-tool.new', {
            parent: 'stop-drilling-tool',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/stop-drilling-tool/stop-drilling-tool-dialog.html',
                    controller: 'StopDrillingToolDialogController',
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
                    $state.go('stop-drilling-tool', null, { reload: 'stop-drilling-tool' });
                }, function() {
                    $state.go('stop-drilling-tool');
                });
            }]
        })
        .state('stop-drilling-tool.edit', {
            parent: 'stop-drilling-tool',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/stop-drilling-tool/stop-drilling-tool-dialog.html',
                    controller: 'StopDrillingToolDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['StopDrillingTool', function(StopDrillingTool) {
                            return StopDrillingTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('stop-drilling-tool', null, { reload: 'stop-drilling-tool' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('stop-drilling-tool.delete', {
            parent: 'stop-drilling-tool',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/stop-drilling-tool/stop-drilling-tool-delete-dialog.html',
                    controller: 'StopDrillingToolDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['StopDrillingTool', function(StopDrillingTool) {
                            return StopDrillingTool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('stop-drilling-tool', null, { reload: 'stop-drilling-tool' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
