(function() {
    'use strict';

    angular
        .module('taraApp')
        .factory('PositioningSystemSearch', PositioningSystemSearch);

    PositioningSystemSearch.$inject = ['$resource'];

    function PositioningSystemSearch($resource) {
        var resourceUrl =  'api/_search/positioning-systems/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
