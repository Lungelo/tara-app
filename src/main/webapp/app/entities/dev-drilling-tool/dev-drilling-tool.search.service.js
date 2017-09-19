(function() {
    'use strict';

    angular
        .module('taraApp')
        .factory('DevDrillingToolSearch', DevDrillingToolSearch);

    DevDrillingToolSearch.$inject = ['$resource'];

    function DevDrillingToolSearch($resource) {
        var resourceUrl =  'api/_search/dev-drilling-tools/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
