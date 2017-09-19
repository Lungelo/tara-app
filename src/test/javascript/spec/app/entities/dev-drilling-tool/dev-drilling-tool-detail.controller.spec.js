'use strict';

describe('Controller Tests', function() {

    describe('DevDrillingTool Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockDevDrillingTool, MockCompany, MockUser;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockDevDrillingTool = jasmine.createSpy('MockDevDrillingTool');
            MockCompany = jasmine.createSpy('MockCompany');
            MockUser = jasmine.createSpy('MockUser');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'DevDrillingTool': MockDevDrillingTool,
                'Company': MockCompany,
                'User': MockUser
            };
            createController = function() {
                $injector.get('$controller')("DevDrillingToolDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'taraApp:devDrillingToolUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
