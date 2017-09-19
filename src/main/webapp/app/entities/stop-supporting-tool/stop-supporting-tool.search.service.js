(function() {
    'use strict';

    angular
        .module('taraApp')
        .factory('StopSupportingToolSearch', StopSupportingToolSearch);

    StopSupportingToolSearch.$inject = ['$resource'];

    function StopSupportingToolSearch($resource) {
        var resourceUrl =  'api/_search/stop-supporting-tools/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
