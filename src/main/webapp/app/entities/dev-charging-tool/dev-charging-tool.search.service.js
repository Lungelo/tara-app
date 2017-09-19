(function() {
    'use strict';

    angular
        .module('taraApp')
        .factory('DevChargingToolSearch', DevChargingToolSearch);

    DevChargingToolSearch.$inject = ['$resource'];

    function DevChargingToolSearch($resource) {
        var resourceUrl =  'api/_search/dev-charging-tools/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
