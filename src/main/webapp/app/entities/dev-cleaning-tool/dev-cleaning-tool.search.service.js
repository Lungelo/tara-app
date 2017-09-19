(function() {
    'use strict';

    angular
        .module('taraApp')
        .factory('DevCleaningToolSearch', DevCleaningToolSearch);

    DevCleaningToolSearch.$inject = ['$resource'];

    function DevCleaningToolSearch($resource) {
        var resourceUrl =  'api/_search/dev-cleaning-tools/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
