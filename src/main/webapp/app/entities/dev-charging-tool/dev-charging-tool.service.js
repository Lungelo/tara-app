(function() {
    'use strict';
    angular
        .module('taraApp')
        .factory('DevChargingTool', DevChargingTool);

    DevChargingTool.$inject = ['$resource'];

    function DevChargingTool ($resource) {
        var resourceUrl =  'api/dev-charging-tools/:id';

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
