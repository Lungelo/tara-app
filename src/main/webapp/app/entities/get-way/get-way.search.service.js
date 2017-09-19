(function() {
    'use strict';

    angular
        .module('taraApp')
        .factory('GetWaySearch', GetWaySearch);

    GetWaySearch.$inject = ['$resource'];

    function GetWaySearch($resource) {
        var resourceUrl =  'api/_search/get-ways/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
