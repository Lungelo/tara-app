(function() {
    'use strict';

    angular
        .module('taraApp')
        .factory('DevSupportingToolSearch', DevSupportingToolSearch);

    DevSupportingToolSearch.$inject = ['$resource'];

    function DevSupportingToolSearch($resource) {
        var resourceUrl =  'api/_search/dev-supporting-tools/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
