(function() {
    'use strict';
    angular
        .module('taraApp')
        .factory('StopDrillingTool', StopDrillingTool);

    StopDrillingTool.$inject = ['$resource'];

    function StopDrillingTool ($resource) {
        var resourceUrl =  'api/stop-drilling-tools/:id';

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
