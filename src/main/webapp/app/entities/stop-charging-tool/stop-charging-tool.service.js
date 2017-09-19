(function() {
    'use strict';
    angular
        .module('taraApp')
        .factory('StopChargingTool', StopChargingTool);

    StopChargingTool.$inject = ['$resource'];

    function StopChargingTool ($resource) {
        var resourceUrl =  'api/stop-charging-tools/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
