(function() {
    'use strict';

    angular
        .module('taraApp')
        .factory('SensorSearch', SensorSearch);

    SensorSearch.$inject = ['$resource'];

    function SensorSearch($resource) {
        var resourceUrl =  'api/_search/sensors/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
