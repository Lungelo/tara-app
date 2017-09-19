(function() {
    'use strict';

    angular
        .module('taraApp')
        .factory('StopCleaningToolSearch', StopCleaningToolSearch);

    StopCleaningToolSearch.$inject = ['$resource'];

    function StopCleaningToolSearch($resource) {
        var resourceUrl =  'api/_search/stop-cleaning-tools/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
