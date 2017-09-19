(function() {
    'use strict';

    angular
        .module('taraApp')
        .factory('WirelessSensorNetworkSearch', WirelessSensorNetworkSearch);

    WirelessSensorNetworkSearch.$inject = ['$resource'];

    function WirelessSensorNetworkSearch($resource) {
        var resourceUrl =  'api/_search/wireless-sensor-networks/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
