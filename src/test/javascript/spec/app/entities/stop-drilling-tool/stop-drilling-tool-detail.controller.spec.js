'use strict';

describe('Controller Tests', function() {

    describe('StopDrillingTool Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockStopDrillingTool, MockCompany, MockUser;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockStopDrillingTool = jasmine.createSpy('MockStopDrillingTool');
            MockCompany = jasmine.createSpy('MockCompany');
            MockUser = jasmine.createSpy('MockUser');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'StopDrillingTool': MockStopDrillingTool,
                'Company': MockCompany,
                'User': MockUser
            };
            createController = function() {
                $injector.get('$controller')("StopDrillingToolDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'taraApp:stopDrillingToolUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
