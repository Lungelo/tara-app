(function() {
    'use strict';

    angular
        .module('taraApp')
        .factory('SensorNodeSearch', SensorNodeSearch);

    SensorNodeSearch.$inject = ['$resource'];

    function SensorNodeSearch($resource) {
        var resourceUrl =  'api/_search/sensor-nodes/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
