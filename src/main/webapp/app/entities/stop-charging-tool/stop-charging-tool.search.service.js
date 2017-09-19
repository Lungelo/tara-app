(function() {
    'use strict';

    angular
        .module('taraApp')
        .factory('StopChargingToolSearch', StopChargingToolSearch);

    StopChargingToolSearch.$inject = ['$resource'];

    function StopChargingToolSearch($resource) {
        var resourceUrl =  'api/_search/stop-charging-tools/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
