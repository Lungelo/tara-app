'use strict';

describe('Controller Tests', function() {

    describe('StopSupportingTool Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockStopSupportingTool, MockCompany, MockUser;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockStopSupportingTool = jasmine.createSpy('MockStopSupportingTool');
            MockCompany = jasmine.createSpy('MockCompany');
            MockUser = jasmine.createSpy('MockUser');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'StopSupportingTool': MockStopSupportingTool,
                'Company': MockCompany,
                'User': MockUser
            };
            createController = function() {
                $injector.get('$controller')("StopSupportingToolDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'taraApp:stopSupportingToolUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
