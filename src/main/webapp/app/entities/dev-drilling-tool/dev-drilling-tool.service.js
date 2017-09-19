(function() {
    'use strict';
    angular
        .module('taraApp')
        .factory('DevDrillingTool', DevDrillingTool);

    DevDrillingTool.$inject = ['$resource'];

    function DevDrillingTool ($resource) {
        var resourceUrl =  'api/dev-drilling-tools/:id';

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
