(function() {
    'use strict';
    angular
        .module('taraApp')
        .factory('PositioningSystem', PositioningSystem);

    PositioningSystem.$inject = ['$resource'];

    function PositioningSystem ($resource) {
        var resourceUrl =  'api/positioning-systems/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
