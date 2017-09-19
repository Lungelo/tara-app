'use strict';

describe('Controller Tests', function() {

    describe('DevCleaningTool Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockDevCleaningTool, MockCompany, MockUser;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockDevCleaningTool = jasmine.createSpy('MockDevCleaningTool');
            MockCompany = jasmine.createSpy('MockCompany');
            MockUser = jasmine.createSpy('MockUser');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'DevCleaningTool': MockDevCleaningTool,
                'Company': MockCompany,
                'User': MockUser
            };
            createController = function() {
                $injector.get('$controller')("DevCleaningToolDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'taraApp:devCleaningToolUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
