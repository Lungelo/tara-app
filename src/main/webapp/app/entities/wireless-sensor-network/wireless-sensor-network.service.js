(function() {
    'use strict';
    angular
        .module('taraApp')
        .factory('WirelessSensorNetwork', WirelessSensorNetwork);

    WirelessSensorNetwork.$inject = ['$resource'];

    function WirelessSensorNetwork ($resource) {
        var resourceUrl =  'api/wireless-sensor-networks/:id';

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
