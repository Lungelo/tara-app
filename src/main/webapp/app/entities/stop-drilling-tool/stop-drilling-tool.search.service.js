(function() {
    'use strict';

    angular
        .module('taraApp')
        .factory('StopDrillingToolSearch', StopDrillingToolSearch);

    StopDrillingToolSearch.$inject = ['$resource'];

    function StopDrillingToolSearch($resource) {
        var resourceUrl =  'api/_search/stop-drilling-tools/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
