(function() {
    'use strict';
    angular
        .module('taraApp')
        .factory('SensorNode', SensorNode);

    SensorNode.$inject = ['$resource'];

    function SensorNode ($resource) {
        var resourceUrl =  'api/sensor-nodes/:id';

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
